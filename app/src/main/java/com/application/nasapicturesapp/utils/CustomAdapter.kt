package com.application.nasapicturesapp.utils


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.application.nasapicturesapp.R
import com.bumptech.glide.Glide


object CustomAdapter {
    @JvmStatic
    @BindingAdapter("bindImage")
    fun bindImage(view: ImageView, url: String?) {
        if (!url.isNullOrBlank() && url.isNotEmpty()) {
            Glide.with(view.context)
                .load(url)
                .error(R.drawable.notfound)
                .into(view)
        } else {
            view.setImageResource(R.drawable.notfound)
        }


    }

}