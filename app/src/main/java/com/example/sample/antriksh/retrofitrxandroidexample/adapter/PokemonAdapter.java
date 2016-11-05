package com.example.sample.antriksh.retrofitrxandroidexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;

import java.util.List;

public class PokemonAdapter extends
        RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> placesList;

    public PokemonAdapter(List<Pokemon> placesList) {
        this.placesList = placesList;
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
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_layout, viewGroup, false);

        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder placesViewHolder, int position) {
        Pokemon ci = placesList.get(position);
        placesViewHolder.nome.setText(ci.getName());
        placesViewHolder.rankDef.setText(ci.getRankDef());
        placesViewHolder.rankOff.setText(ci.getRankOff());


    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        protected TextView nome;
        protected TextView rankDef;
        protected TextView rankOff;
        protected LinearLayout container;

        public PokemonViewHolder(View v) {
            super(v);
            container = (LinearLayout) v.findViewById(R.id.item_layout_container);
            nome = (TextView) v.findViewById(R.id.textName);
            rankOff = (TextView) v.findViewById(R.id.textRankOff);
            rankDef = (TextView) v.findViewById(R.id.textRankDef);

        }
    }
}
