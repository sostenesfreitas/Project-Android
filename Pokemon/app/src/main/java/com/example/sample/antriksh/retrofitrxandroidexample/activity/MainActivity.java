package com.example.sample.antriksh.retrofitrxandroidexample.activity;



import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;
import com.example.sample.antriksh.retrofitrxandroidexample.databinding.ActivityMainBinding;
import com.example.sample.antriksh.retrofitrxandroidexample.fragment.DetailFragment;
import com.example.sample.antriksh.retrofitrxandroidexample.fragment.FavorFragment;
import com.example.sample.antriksh.retrofitrxandroidexample.fragment.MainFragment;


public class MainActivity extends AppCompatActivity implements PokeClick{
    ActivityMainBinding mBinding;
    MainFragment mainFragment;
    FavorFragment favorFragment;
    SelectorPageAdapter selectorPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mBinding.toolbar);

        buildViewPager();
    }

    private void buildViewPager(){
        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());
        mBinding.pager.setAdapter(selectorPageAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.pager);
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

    public class  SelectorPageAdapter extends FragmentPagerAdapter{

        public SelectorPageAdapter(FragmentManager fm) {super(fm);}

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mainFragment == null){
                        mainFragment = new MainFragment();
                    }
                    return mainFragment;
                case 1:
                default:
                    if(favorFragment == null){
                        favorFragment = new FavorFragment();
                    }
                    return favorFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Lista";
                case 1:
                default:
                    return "Favoritos";
            }

        }
    }


}
