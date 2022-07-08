package com.application.nasapicturesapp.respository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.nasapicturesapp.model.PicturesModel


@Database(
    entities = [PicturesModel::class],
    version = 1,
    exportSchema = true
)
abstract class NasaAppDb : RoomDatabase() {

    abstract fun picturesDao(): PicturesDao

    companion object {
        @Volatile
        private var instance: NasaAppDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            NasaAppDb::class.java, "NasaApp.db"
        ).build()
    }
}