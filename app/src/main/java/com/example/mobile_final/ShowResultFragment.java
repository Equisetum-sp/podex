package com.example.mobile_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowResultFragment extends Fragment {
    public static final String TAG_FRAGMENT = "Show Result";
    public static final String TAG_POKEMONLIST = "Pokemon List";
    public static final String TAG_TITLE = "TITLE";

    String title;
    ArrayList<Pokemon> pokemonList;
    PokemonAdapter rvAdapter;

    public ShowResultFragment() {
        // Required empty public constructor
    }


    public static ShowResultFragment newInstance(Bundle args) {
        ShowResultFragment fragment = new ShowResultFragment();

        fragment.setArguments(args);

        return fragment;
    }


    /*
    protected void buttonHandlerSelectPokemon(View view, Pokemon curr){
        Bundle bundle = new Bundle();
        bundle.putParcelable(ViewActivity.TAG_POKEMON, curr);

        Intent i = new Intent(getActivity(), ViewActivity.class);
        i.putExtras(bundle);
        i.putExtra(ViewActivity.TAG_POKEMON, curr.getId());
        startActivity(i);
    }
    */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = requireArguments().getString(TAG_TITLE);
        pokemonList = requireArguments().getParcelableArrayList(TAG_POKEMONLIST);

        TextView titleTextView = (TextView) view.findViewById(R.id.showres_title);
        if (pokemonList != null) {
            RecyclerView recyclerPokemon = (RecyclerView) view.findViewById(R.id.showres_recyclerpokemon);
            rvAdapter = new PokemonAdapter(pokemonList, getActivity());
            recyclerPokemon.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerPokemon.setAdapter(rvAdapter);


            titleTextView.setText(title);
        }
        else{
            titleTextView.setText(title + ": No Result Found");
        }
    }
}