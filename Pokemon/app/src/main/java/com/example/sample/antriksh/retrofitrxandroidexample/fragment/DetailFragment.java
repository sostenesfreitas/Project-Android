package com.example.sample.antriksh.retrofitrxandroidexample.fragment;

import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;

import com.example.sample.antriksh.retrofitrxandroidexample.database.DbEvent;
import com.example.sample.antriksh.retrofitrxandroidexample.database.PokeDAO;
import com.example.sample.antriksh.retrofitrxandroidexample.databinding.FragmentDetailBinding;
import com.google.common.eventbus.EventBus;


public class DetailFragment extends Fragment {
    Pokemon pokemon;
    FragmentDetailBinding mBinding;
    PokeDAO pd = new PokeDAO(getActivity());

    public static DetailFragment newInstace(Pokemon pokemon){
        Bundle bundle = new Bundle();
        bundle.putSerializable("pokemon", pokemon);
        DetailFragment df = new DetailFragment();
        df.setArguments(bundle);
        return df;
    }

    public DetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.
                inflate(inflater,R.layout.fragment_detail, container, false);

         pokemon = (Pokemon) getArguments().getSerializable("pokemon");

         mBinding.setPokemons(pokemon);

        Pokemon tempPokemon = pd.getPoke(pokemon.getName());

        if(pokemon.getName().equals(tempPokemon.getName())){
            changeFloatButton(true);
        }else {
            changeFloatButton(false);
        }
        mBinding.fbutton.setOnClickListener(View -> saveOrRemoveFavorite());

        return mBinding.getRoot();
    }

    public void saveOrRemoveFavorite(){
        Pokemon tempPokemon = pd.getPoke(pokemon.getName());

        if(tempPokemon.get_id() != null){
            pd.delPoke(pokemon.getName());
            changeFloatButton(false);
        }else{
            pd.inserir(pokemon);
            changeFloatButton(true);
        }
        org.greenrobot.eventbus.EventBus.getDefault().post(new DbEvent());
    }

    public void changeFloatButton(boolean favor){
        int resource = favor ? R.drawable.ic_close_black_24dp : R.drawable.ic_check_black_24dp;
        mBinding.fbutton.setImageResource(resource);
    }

}
