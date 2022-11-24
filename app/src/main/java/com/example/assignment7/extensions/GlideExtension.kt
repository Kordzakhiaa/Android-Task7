package com.example.assignment7.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setNetworkImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this).load(url).centerCrop()
            .into(this)
    }
}