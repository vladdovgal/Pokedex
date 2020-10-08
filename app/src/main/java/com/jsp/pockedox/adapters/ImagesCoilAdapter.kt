package com.jsp.pockedox.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.jsp.pockedox.R

@BindingAdapter("image")
fun loadWithCoil(imageView: ImageView, imageUrl: String){
    imageView.load(imageUrl){
        placeholder(R.drawable.img_placeholder)
        error(R.drawable.img_error)
    }
}