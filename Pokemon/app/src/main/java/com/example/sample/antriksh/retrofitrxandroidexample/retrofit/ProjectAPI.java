package com.example.sample.antriksh.retrofitrxandroidexample.retrofit;


import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by antrikshtiwari on 09/04/16.
 */
public interface ProjectAPI {

    @GET("/pokemon")
    Observable<List<Pokemon>> getPokemon(@Query("name") String pokemon);

}
