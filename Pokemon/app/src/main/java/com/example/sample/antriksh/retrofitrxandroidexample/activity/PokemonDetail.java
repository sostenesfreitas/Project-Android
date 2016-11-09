package com.example.sample.antriksh.retrofitrxandroidexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;

public class PokemonDetail extends AppCompatActivity {

    TextView name,
            basicAtk,
            chargeAtk,
            chargeDamage,
            rankOff,
            rankDef,
            tank,
            gymOff,
            gymDef,
            damage,
            gynDamage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        Intent it= getIntent();
        Pokemon pokemon = (Pokemon) it.getSerializableExtra("pokemon");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(pokemon.getName());
        setSupportActionBar(toolbar);

        name = (TextView) findViewById(R.id.textName);
        basicAtk = (TextView) findViewById(R.id.textBasicMove);
        chargeAtk = (TextView) findViewById(R.id.textChargeMove);
        chargeDamage = (TextView) findViewById(R.id.textChargeDamage);
        damage = (TextView) findViewById(R.id.textBasciDamage);
        gynDamage = (TextView) findViewById(R.id.textGynDamage);
        tank = (TextView) findViewById(R.id.textGynTank);



        name.setText(pokemon.getName());
        basicAtk.setText(pokemon.getBasicAtk());
        chargeAtk.setText(pokemon.getChargeAtk());
        damage.setText(pokemon.getDamage());
        chargeDamage.setText(pokemon.getChargeDamage());
        gynDamage.setText(pokemon.getGynDamage());
        tank.setText(pokemon.getTank());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
