package com.example.sample.antriksh.retrofitrxandroidexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sample.antriksh.retrofitrxandroidexample.R;

import com.example.sample.antriksh.retrofitrxandroidexample.activity.PokemonDetail;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;
import com.example.sample.antriksh.retrofitrxandroidexample.databinding.CardviewLayoutBinding;

import java.util.List;

public class PokemonAdapter extends
        RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    CardviewLayoutBinding CardBinding;
    private List<Pokemon> placesList;
    Pokemon pokemon;
    private Context mContext;

    public PokemonAdapter(List<Pokemon> placesList, Context context) {
        this.placesList = placesList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {

        if (placesList != null) {
            return placesList.size();
        } else {
            return 0;
        }
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CardBinding = DataBindingUtil.inflate(LayoutInflater.
                from(viewGroup.getContext()),
                R.layout.cardview_layout, viewGroup, false);
        return new PokemonViewHolder(CardBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(final PokemonViewHolder placesViewHolder, int position) {
        pokemon = placesList.get(position);
        placesViewHolder.binding.setPokemon(pokemon);
        placesViewHolder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent it = new Intent(mContext, PokemonDetail.class);
                it.putExtra("pokemon", pokemon);
                mContext.startActivity(it);
            }
        });
    }
    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        CardviewLayoutBinding binding;
        public PokemonViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }
    }
}
