package com.example.mobile_final;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mainSearchInput;
    LinearLayout mainSearchButton;
    FragmentContainerView mainFragmentContainer;
    PokemonList dex;

    private void attachFragment(Bundle bundle){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().setReorderingAllowed(true);

        if (fm.findFragmentByTag(ShowResultFragment.TAG_FRAGMENT) != null){
            dismissFragment();
        }

        ft.add(R.id.main_fragmentcontainer, ShowResultFragment.newInstance(bundle), ShowResultFragment.TAG_FRAGMENT);
        ft.commit();
    }

    private void dismissFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment curr = (Fragment) fm.findFragmentByTag(ShowResultFragment.TAG_FRAGMENT);
        if (curr != null){
            ft.remove(curr).commit();
        }
    }

    private void buttonHandlerSearch(View v){
        String key = mainSearchInput.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(ShowResultFragment.TAG_TITLE, "Search result for '"+ key + "'");
        bundle.putParcelableArrayList(ShowResultFragment.TAG_POKEMONLIST, dex.getPokemon(key));

        attachFragment(bundle);
    }

    protected void buttonHandlerSelectPokemon(View view, Pokemon curr){
        Intent i = new Intent(this, ViewActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ViewActivity.TAG_POKEMON, curr);
            i.putExtras(bundle);
        }
        else {
            i.putExtra(ViewActivity.TAG_POKEMON, curr.getId());
        }
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            dex = PokemonList.getInstance(this.getApplicationContext());
        } catch (Exception e){
            Log.e("MainActivity", e.getMessage());
            throw new RuntimeException(e);
        }

        mainFragmentContainer = (FragmentContainerView) findViewById(R.id.main_fragmentcontainer);

        if (savedInstanceState == null){
            Bundle bundle = new Bundle();
            bundle.putString(ShowResultFragment.TAG_TITLE, "Gen 1 Pokemon");
            bundle.putParcelableArrayList(ShowResultFragment.TAG_POKEMONLIST, dex.getPokemonAll());

            attachFragment(bundle);
        }

        mainSearchInput = (EditText) findViewById(R.id.main_searchinput);

        mainSearchButton = (LinearLayout) findViewById(R.id.main_searchbutton);
        mainSearchButton.setOnClickListener(this::buttonHandlerSearch);
    }
}