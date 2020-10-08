package com.jsp.pockedox.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("image")
fun loadWithCoil(imageView: ImageView, imageUrl: String){
    imageView.load(imageUrl){
        //todo image placeholder
        // todo errorimage
    }
}