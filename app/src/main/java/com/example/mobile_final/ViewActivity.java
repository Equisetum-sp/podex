package com.example.mobile_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* NOTES
* Layout Podex : layoutTitle
Text Podex : viewTitle

Layout Detail : layoutSubTitle
Text Detail : viewSubTitle

Layout Pokemon (Box Kuning) : layoutPokemon
Text Pikachu : viewName
Image Pikachu : viewImg
Number Pikachu : viewNum

Layout Stats (Box Putih) : layoutStats
Layout Text About : layoutAbout
Text About : viewStatsAbout
Layout Isi About : layoutTextAbout
Isi About : viewStatsTextAbout

Layout Specs (Box Dark Yellow) : layoutSpecs
Number Height : heightNum
Number Weight : weightNum

Layout Voice : layoutVoice
Text Voice : viewStatsVoice
Layout Isi Voice : layoutTextVoice
Isi Voice : viewStatsTextVoice
Voice Button : viewButtonVoice

*
* */
public class ViewActivity extends AppCompatActivity {
    public static final String TAG_POKEMON = "Pokemon";
    public static final String TAG_POKEMONLEGACY = "Pokemon LEGACY";

    //region view variables
    LinearLayout layoutPokemon;
    TextView dexno, name, about, height, weight, HP, atk, def, spc, spe, locRed, locBlue, locYellow;
    ImageView sprite;
    //endregion


    Pokemon pokemon;
    MediaPlayer soundPlayer;

    private void showPokemonData(){
        layoutPokemon.getBackground().setColorFilter(new BlendModeColorFilter(pokemon.getTypes()[0].getColor(), BlendMode.SRC_ATOP));

        dexno.setText("#" + String.format("%04d", pokemon.getId()));
        name.setText(pokemon.getName());
        about.setText(pokemon.getAbout());
        height.setText(Double.toString(pokemon.getHeight()) + " m");
        weight.setText(Double.toString(pokemon.getWeight()) + " kg");
        HP.setText(Integer.toString(pokemon.getStat().getHp()));
        atk.setText(Integer.toString(pokemon.getStat().getAtk()));
        def.setText(Integer.toString(pokemon.getStat().getDef()));
        spc.setText(Integer.toString(pokemon.getStat().getSpc()));
        spe.setText(Integer.toString(pokemon.getStat().getSpe()));
        locRed.setText(pokemon.getLocationRed());
        locBlue.setText(pokemon.getLocationBlue());
        locYellow.setText(pokemon.getLocationYellow());

        sprite.setImageDrawable(pokemon.getSprite(this));

        soundPlayer = pokemon.getSoundPlayer(this);
    }

    private void buttonHandlerPlaySound(View v){
        soundPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        layoutPokemon = (LinearLayout) findViewById(R.id.viewLayoutPokemon);

        dexno = (TextView) findViewById(R.id.viewDexNo);
        name = (TextView) findViewById(R.id.viewName);
        about = (TextView) findViewById(R.id.viewStatsTextAbout);
        height = (TextView) findViewById(R.id.viewHeight);
        weight = (TextView) findViewById(R.id.viewWeight);
        HP = (TextView) findViewById(R.id.viewStatsHPNum);
        atk = (TextView) findViewById(R.id.viewStatsAtkNum);
        def = (TextView) findViewById(R.id.viewStatsDefNum);
        spc = (TextView) findViewById(R.id.viewStatsSpcNum);
        spe = (TextView) findViewById(R.id.viewStatsSpeNum);
        locRed = (TextView) findViewById(R.id.viewStatsRedLocation);
        locBlue = (TextView) findViewById(R.id.viewStatsBlueLocation);
        locYellow = (TextView) findViewById(R.id.viewStatsYellowLocation);

        sprite = (ImageView) findViewById(R.id.viewImg);

        Button cry = findViewById(R.id.viewButtonVoice);
        cry.setOnClickListener(this::buttonHandlerPlaySound);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pokemon = i.getExtras().getParcelable(TAG_POKEMON, Pokemon.class);
            if (pokemon == null){
                Log.e("ViewActivity", "NullPointer: Pokemon is null");
            }
        }
        else{
            int dexno = i.getIntExtra(TAG_POKEMONLEGACY, 1);
            try {
                pokemon = PokemonList.getInstance(this).getPokemon(dexno).get(0);
            }catch (Exception e){
                Log.e("ViewActivity", e.getMessage());
                return;
            }
        }

        if (pokemon != null){
            showPokemonData();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Release the MediaPlayer resources
        if (soundPlayer != null) {
            soundPlayer.release();
            soundPlayer = null;
        }
    }
}