package com.example.sample.antriksh.retrofitrxandroidexample.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PokeId  implements Serializable {
    @SerializedName("$oid")
   private String id;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

}
