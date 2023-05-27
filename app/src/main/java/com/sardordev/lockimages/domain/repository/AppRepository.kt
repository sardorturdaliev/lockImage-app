package com.sardordev.lockimages.domain.repository

import androidx.lifecycle.LiveData
import com.sardordev.lockimages.data.model.AlbumData
import com.sardordev.lockimages.data.model.ImageData
import com.sardordev.lockimages.data.model.SavedImg

interface AppRepository {
    fun insert(imageData: ImageData)
    fun getAllImages(): LiveData<List<ImageData>>
    fun update(imageData: ImageData)
    fun delete()
    fun getIsremember(): LiveData<List<ImageData>>

    /*
    Album
     */
    fun createAlbum(albumData: AlbumData)
    fun getAlbum(): LiveData<List<AlbumData>>
    fun updateAlbum(albumData: AlbumData)

    /*
    Save Img selected
     */

    fun saveImgSelected(savedImg: SavedImg)
    fun getImgById(idkey: Int) :LiveData<List<SavedImg>>

}