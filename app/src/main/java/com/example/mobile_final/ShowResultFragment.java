package com.example.mobile_final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ShowResultFragment extends Fragment {
    public static final String TAG_FRAGMENT = "Show Result";
    public static final String TAG_POKEMONLIST = "Pokemon List";
    public static final String TAG_TITLE = "TITLE";

    String title;
    ArrayList<Pokemon> pokemonList;
    PokemonAdapter rvAdapter;

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

        RecyclerView recyclerPokemon = (RecyclerView) view.findViewById(R.id.showres_recyclerpokemon);
        rvAdapter = new PokemonAdapter(pokemonList, getActivity());
        recyclerPokemon.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPokemon.setAdapter(rvAdapter);

    }
}