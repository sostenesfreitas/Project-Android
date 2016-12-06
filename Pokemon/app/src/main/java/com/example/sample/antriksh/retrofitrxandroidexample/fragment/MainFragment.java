package com.example.sample.antriksh.retrofitrxandroidexample.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.adapter.PokemonAdapter;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;
import com.example.sample.antriksh.retrofitrxandroidexample.databinding.FragmentMainBinding;
import com.example.sample.antriksh.retrofitrxandroidexample.retrofit.ProjectAPI;
import com.example.sample.antriksh.retrofitrxandroidexample.retrofit.RetrofitService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainFragment extends Fragment implements SearchView.OnQueryTextListener{
    FragmentMainBinding mBinding;
    private Subscription subscription;

    public MainFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main, container, false);

        EventBus.getDefault().register(this);

        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void callServerPokemon(String pokemon) {
        final ProjectAPI service = RetrofitService.createRetrofitClient();
        subscription = service.getPokemon(pokemon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemons -> EventBus.getDefault().postSticky(pokemons),
                        Throwable::printStackTrace,
                        () -> subscription.unsubscribe());}


    private void findViewAndSetAdapter(List<Pokemon> pokemonApi) {

        mBinding.cardList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mBinding.cardList.setLayoutManager(llm);

        PokemonAdapter ca = new PokemonAdapter(pokemonApi, getActivity());

        mBinding.cardList.setAdapter(ca);

        ca.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query != null)
            callServerPokemon(query);
        else
            Toast.makeText(getContext(), "Consulta esta em branco", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
    @Subscribe(sticky = true ,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Pokemon> event) {findViewAndSetAdapter(event);}
}
