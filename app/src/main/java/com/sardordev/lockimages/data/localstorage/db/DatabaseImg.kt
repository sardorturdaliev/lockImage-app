package com.sardordev.lockimages.data.localstorage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sardordev.lockimages.data.model.AlbumData
import com.sardordev.lockimages.data.model.ImageData
import com.sardordev.lockimages.data.model.SavedImg

@Database(entities = [ImageData::class, AlbumData::class, SavedImg::class], version = 2)
abstract class DatabaseImg : RoomDatabase() {
    abstract fun imgDao(): ImgDao
    abstract fun albumDao(): AlbumDao
    abstract fun savedImg(): SavedImgDao


    companion object {
        private var instance: DatabaseImg? = null
        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, DatabaseImg::class.java, "imgsave")
                    .allowMainThreadQueries().build()
            }
        }

        fun getInstance() = instance!!
    }

}