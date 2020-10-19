package com.epam.pockedox.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.epam.pockedox.R

@BindingAdapter("image")
fun loadWithCoil(imageView: ImageView, imageUrl: String){
    imageView.load(imageUrl){
        placeholder(R.drawable.img_placeholder)
        error(R.drawable.img_error)
    }
}
