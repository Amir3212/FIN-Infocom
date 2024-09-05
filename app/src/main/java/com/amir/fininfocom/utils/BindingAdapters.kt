package com.amir.fininfocom.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.amir.fininfocom.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("android:bindImage")
fun bindImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(it)
            .apply(
                RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            )
            .into(view)
    }
}