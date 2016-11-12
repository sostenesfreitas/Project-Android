package com.example.sample.antriksh.retrofitrxandroidexample.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;
import com.example.sample.antriksh.retrofitrxandroidexample.databinding.ActivityPokemonDetailBinding;

public class PokemonDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPokemonDetailBinding mBinding = DataBindingUtil
                .setContentView(this,R.layout.activity_pokemon_detail);

        Pokemon pokemon = (Pokemon) getIntent()
                .getSerializableExtra("pokemon");

        mBinding.toolbar.setTitle(pokemon.getName());
        setSupportActionBar(mBinding.toolbar);
        mBinding.detail.setPokemon(pokemon);

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
