package com.example.sample.antriksh.retrofitrxandroidexample.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.adapter.PokemonAdapter;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;
import com.example.sample.antriksh.retrofitrxandroidexample.retrofit.ProjectAPI;
import com.example.sample.antriksh.retrofitrxandroidexample.retrofit.RetrofitService;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private final String TAG = MainActivity.class.getName();

    private Subscription subscription;

    String pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Snackbar snackbar = Snackbar
                .make(findViewById(R.id.coordinatorLayout),
                        getString(R.string.server_call), Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Do nothing, just dismiss snackbar
                    }
                });

        snackbar.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    private void callServerPokemon(String pokemon) {

        final ProjectAPI service = RetrofitService.createRetrofitClient();

        subscription = service.getPokemon(pokemon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);
    }

    Observer<List<Pokemon>> myObserver = new Observer<List<Pokemon>>() {

        @Override
        public void onCompleted() {
            subscription.unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
            Log.d(TAG, ">>>> onError gets called : " + e.getMessage());
        }

        @Override
        public void onNext(List<Pokemon> pokemons) {
            findViewAndSetAdapter(pokemons);
        }
    };

    private void findViewAndSetAdapter(List<Pokemon> pokemonApi) {

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        PokemonAdapter ca = new PokemonAdapter(pokemonApi);
        recList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        callServerPokemon(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
