package com.example.mobile_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Button PokeCries = findViewById(R.id.viewButtonVoice);
        PokeCries.setOnClickListener(view -> {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.pikachucry);
            mp.start();
        });
    }
}