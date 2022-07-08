package com.application.nasapicturesapp.respository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.application.nasapicturesapp.model.PicturesModel

@Dao
interface PicturesDao {
    @Insert()
    fun addAllPictures(albums: List<PicturesModel>)

    @Query("select * from pictures")
    fun getAllPictures(): LiveData<List<PicturesModel>>
}