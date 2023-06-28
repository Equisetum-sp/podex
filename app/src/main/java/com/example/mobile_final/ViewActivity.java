package com.example.mobile_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Context;
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

    Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Button cry = findViewById(R.id.viewButtonVoice);


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
    }
}