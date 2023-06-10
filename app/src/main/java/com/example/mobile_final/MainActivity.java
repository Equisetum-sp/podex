package com.example.mobile_final;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    PokemonList dex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView test = (TextView) findViewById(R.id.main_test);

        try {
            dex = PokemonList.getInstance(this.getApplicationContext());
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
            throw new RuntimeException(e);
        }

        ArrayList<Pokemon> a = dex.getPokemon(130);
        String testString = a.get(0).getId() + " " + a.get(0).getName() + " " + a.get(0).getTypes()[0].name;
        test.setText(testString);
    }
}