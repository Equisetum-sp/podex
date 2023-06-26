package com.example.mobile_final;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentContainerView mainFragmentContainer;
    PokemonList dex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            dex = PokemonList.getInstance(this.getApplicationContext());
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
            throw new RuntimeException(e);
        }

        mainFragmentContainer = (FragmentContainerView) findViewById(R.id.main_fragmentcontainer);

        if (savedInstanceState == null) {
            ArrayList<Pokemon> a = dex.getPokemonAll();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("Pokemon_LIst", a);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction().setReorderingAllowed(true);
            ft.add(mainFragmentContainer.getId(), ShowResultFragment.class, bundle);
            ft.commit();
        }


        ArrayList<Pokemon> a = dex.getPokemon(130);
        String testString = a.get(0).getId() + " " + a.get(0).getName() + " " + a.get(0).getTypes()[0].name;
    }
}