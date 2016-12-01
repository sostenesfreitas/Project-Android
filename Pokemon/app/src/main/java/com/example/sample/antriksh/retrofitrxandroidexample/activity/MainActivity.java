package com.example.sample.antriksh.retrofitrxandroidexample.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;


public class MainActivity extends AppCompatActivity implements PokeClick{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void Click(Pokemon pokemon) {
        if(getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, DetailActivity.class);
            it.putExtra("pokemon", pokemon);
            this.startActivity(it);
        }else {
            DetailFragment df = DetailFragment.newInstace(pokemon);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail, df, "pokemons")
                    .commit();
        }
    }
}
