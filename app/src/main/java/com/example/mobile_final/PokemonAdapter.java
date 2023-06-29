package com.example.mobile_final;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.Holder>{
    class Holder extends RecyclerView.ViewHolder{
        public TextView dexno, name, type1, type2;
        public ImageView sprite;
        public ConstraintLayout layout;
        public Holder(View itemView){
            super(itemView);
            layout = (ConstraintLayout) itemView.findViewById(R.id.list_layout);
            sprite = (ImageView) itemView.findViewById(R.id.list_sprite);
            dexno = (TextView) itemView.findViewById(R.id.list_dexno);
            name = (TextView) itemView.findViewById(R.id.list_name);
            type1 = (TextView) itemView.findViewById(R.id.list_type1);
            type2 = (TextView) itemView.findViewById(R.id.list_type2);
        }
    }

    ArrayList<Pokemon> itemList;

    Context activityContext;

    public PokemonAdapter(ArrayList<Pokemon> list, Context activityContext) {
        itemList = list;
        this.activityContext = activityContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.list_pokemon, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Pokemon curr = itemList.get(position);

        holder.sprite.setImageDrawable(curr.getSprite(activityContext));

        holder.dexno.setText(Integer.toString(curr.getId()));
        holder.name.setText(curr.getName());

        holder.type1.setText(curr.getTypes()[0].getName());
        holder.type1.setBackgroundColor(curr.getTypes()[0].getColor());

        if (curr.getTypes()[1] != null){
            holder.type2.setText(curr.getTypes()[1].getName());
            holder.type2.setBackgroundColor(curr.getTypes()[1].getColor());
        }
        else{
            holder.type2.setVisibility(View.INVISIBLE);
        }

        holder.layout.setOnClickListener(v -> ((MainActivity)activityContext).buttonHandlerSelectPokemon(v, curr));

        /*
        holder.roomContainer.setOnClickListener(v -> ((MainActivity)activityContext).buttonHandlerSelectRoom(v, position));
        */
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
