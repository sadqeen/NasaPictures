package com.application.nasapicturesapp.utils

import android.widget.ImageView
import com.application.nasapicturesapp.model.PicturesModel

interface ItemCallback {

    fun pictureClicked(view: ImageView,position:Int)
    fun onLoadCompleted(view: ImageView?, adapterPosition: Int)
}