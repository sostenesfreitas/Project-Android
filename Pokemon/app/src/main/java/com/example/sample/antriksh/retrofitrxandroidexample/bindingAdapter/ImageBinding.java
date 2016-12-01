package com.example.sample.antriksh.retrofitrxandroidexample.bindingAdapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageBinding {
    @BindingAdapter({"android:src"})
    public static void setImgUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load("https://pokemongobestmoves.herokuapp.com/image?name="+url)
                .asGif()
                .centerCrop()
                .into(imageView);
    }
}
