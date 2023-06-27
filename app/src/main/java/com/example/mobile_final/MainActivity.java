package com.example.mobile_final;

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

        ft.add(mainFragmentContainer.getId(), ShowResultFragment.class, bundle, ShowResultFragment.TAG_FRAGMENT);
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
            bundle.putParcelableArrayList("Pokemon_List", dex.getPokemonAll());
            bundle.putString("title", "Gen 1 Pokemon");

            attachFragment(bundle);
        }

        mainSearchInput = (EditText) findViewById(R.id.main_searchinput);

        mainSearchButton = (LinearLayout) findViewById(R.id.main_searchbutton);
        mainSearchButton.setOnClickListener(this::buttonHandlerSearch);
    }
}