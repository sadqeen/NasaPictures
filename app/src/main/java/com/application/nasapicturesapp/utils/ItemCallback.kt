package com.application.nasapicturesapp.utils

import com.application.nasapicturesapp.model.PicturesModel

interface ItemCallback {

    fun pictureClicked(picturesModel: PicturesModel)
}