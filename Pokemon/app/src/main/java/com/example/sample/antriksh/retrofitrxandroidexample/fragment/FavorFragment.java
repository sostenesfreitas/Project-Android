package com.example.sample.antriksh.retrofitrxandroidexample.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.adapter.PokemonAdapter;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;
import com.example.sample.antriksh.retrofitrxandroidexample.database.DbEvent;
import com.example.sample.antriksh.retrofitrxandroidexample.database.PokeDAO;
import com.example.sample.antriksh.retrofitrxandroidexample.databinding.FragmentFavorBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FavorFragment extends Fragment {
    FragmentFavorBinding mBinding;

    List<Pokemon> pokes = new ArrayList<>();

    public FavorFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           mBinding = DataBindingUtil.inflate(inflater ,
                R.layout.fragment_favor, container, false);
        EventBus.getDefault().register(this);
        updateUi();
        return mBinding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void updateUi(){

        pokes = new PokeDAO(getActivity()).getPokes();
        findViewAndSetAdapter(pokes);
    }

    @Subscribe(sticky = true ,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DbEvent event) {updateUi();}

    private void findViewAndSetAdapter(List<Pokemon> pokemonApi) {

        mBinding.cardList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mBinding.cardList.setLayoutManager(llm);

        PokemonAdapter ca = new PokemonAdapter(pokemonApi, getActivity());

        mBinding.cardList.setAdapter(ca);

        ca.notifyDataSetChanged();
    }


}
