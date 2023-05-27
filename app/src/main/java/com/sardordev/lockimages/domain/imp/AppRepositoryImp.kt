package com.sardordev.lockimages.domain.imp

import android.content.Context
import androidx.lifecycle.LiveData
import com.sardordev.lockimages.data.localstorage.db.DatabaseImg
import com.sardordev.lockimages.data.model.AlbumData
import com.sardordev.lockimages.data.model.ImageData
import com.sardordev.lockimages.data.model.SavedImg
import com.sardordev.lockimages.domain.repository.AppRepository

class AppRepositoryImp private constructor(private val db: DatabaseImg) : AppRepository {

    override fun insert(imageData: ImageData) {
        db.imgDao().insert(imageData)
    }

    override fun getAllImages(): LiveData<List<ImageData>> = db.imgDao().getAllImages()
    override fun update(imageData: ImageData) {
        db.imgDao().undate(imageData)
    }

    override fun delete() {
        db.imgDao().delete()
    }

    override fun getIsremember(): LiveData<List<ImageData>> = db.imgDao().getIsremember()
    override fun createAlbum(albumData: AlbumData) {
        db.albumDao().insert(albumData)
    }

    override fun getAlbum(): LiveData<List<AlbumData>> = db.albumDao().getAlbum()
    override fun updateAlbum(albumData: AlbumData) {
        db.albumDao().update(albumData)
    }

    override fun saveImgSelected(savedImg: SavedImg) {
        db.savedImg().insert(savedImg)
    }

    override fun getImgById(idkey: Int):LiveData<List<SavedImg>> = db.savedImg().getDataById(idkey)


    companion object {
        private lateinit var repository: AppRepositoryImp

        fun init(context: Context) {
            if (!::repository.isInitialized) {
                repository = AppRepositoryImp(DatabaseImg.getInstance())
            }
        }

        fun getInstance(): AppRepositoryImp = repository

    }
}