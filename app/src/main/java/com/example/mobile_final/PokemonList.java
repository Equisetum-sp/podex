package com.example.mobile_final;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Singleton
public class PokemonList {
    private static PokemonList instance = null;
    private ArrayList<Pokemon> pokemons = null;

    private PokemonList(Context context) throws CsvValidationException, IOException {
        CSVReader reader;

        //region read csv
        try {
            reader = new CSVReader(new InputStreamReader(context.getResources().openRawResource(R.raw.pokedata)));
        }catch (Exception e){
            Log.e("PokemonList", e.getMessage());
            return;
        }
        //endregion

        //region fetch data
        pokemons = new ArrayList<Pokemon>();

        String[] record = null;
        record = reader.readNext(); //skip one row
        while ((record = reader.readNext()) != null){
            Stat newStat = new Stat(Integer.parseInt(record[4]),
                                    Integer.parseInt(record[5]),
                                    Integer.parseInt(record[6]),
                                    Integer.parseInt(record[7]),
                                    Integer.parseInt(record[8]));

            Pokemon newPokemon = new Pokemon(Integer.parseInt(record[0]),
                                            record[1],
                                            Double.parseDouble(record[2]),
                                            Double.parseDouble(record[3]),
                                            newStat,
                                            TypeList.getType(record[9]),
                                            (record[10].equals("-")) ? null : TypeList.getType(record[10]),
                                            record[11]+" "+record[12],
                                            new String[]{record[13], record[14], record[15]});
            pokemons.add(newPokemon);
        }
        //endregion
        reader.close();
    }

    public static synchronized PokemonList getInstance(Context context) throws CsvValidationException, IOException {
        if (instance == null){
            instance = new PokemonList(context);
        }
        return instance;
    }

    public ArrayList<Pokemon> getPokemon(int id){
        ArrayList<Pokemon> a = new ArrayList<>();
        if (id > 0 && id <= 151){
            a.add(this.pokemons.get(id-1));
            return a;
        }
        else{
            return null;
        }
    }

    public ArrayList<Pokemon> getPokemon(String key){
        ArrayList<Pokemon> a = new ArrayList<>();
        for (int i=0; i<this.pokemons.size(); i++){
            if (this.pokemons.get(i).getName().contains(key)){
                a.add(this.pokemons.get(i));
            }
        }

        return (a.isEmpty()) ? null : a;
    }

    public ArrayList<Pokemon> getPokemonAll(){
        return pokemons;
    }
}
