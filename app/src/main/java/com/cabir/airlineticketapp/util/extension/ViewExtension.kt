package com.cabir.airlineticketapp.util.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("app:imageUrl")
fun ImageView.loadImage(url: String){
    val newUrl = url.replace("\\/","/")
    Glide.with(context).load(newUrl).into(this)
}