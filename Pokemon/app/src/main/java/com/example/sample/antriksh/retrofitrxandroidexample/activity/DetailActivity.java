package com.example.sample.antriksh.retrofitrxandroidexample.activity;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;
import com.example.sample.antriksh.retrofitrxandroidexample.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");
        DetailFragment df = DetailFragment.newInstace(pokemon);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail, df, "pokemons")
                .commit();
    }
}
