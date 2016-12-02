package com.example.sample.antriksh.retrofitrxandroidexample.fragment;

import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;

import com.example.sample.antriksh.retrofitrxandroidexample.database.PokeDAO;
import com.example.sample.antriksh.retrofitrxandroidexample.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {

   FragmentDetailBinding mBinding;

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
        mBinding.setPokemons((Pokemon) getArguments().getSerializable("pokemon"));
        mBinding.fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokeDAO pd = new PokeDAO(getActivity());
                pd.inserir((Pokemon)getArguments().getSerializable("pokemon"));
            }
        });
        return mBinding.getRoot();
    }

}
