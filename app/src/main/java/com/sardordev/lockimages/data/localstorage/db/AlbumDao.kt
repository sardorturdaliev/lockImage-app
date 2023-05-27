package com.sardordev.lockimages.data.localstorage.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sardordev.lockimages.constants.GetConstants
import com.sardordev.lockimages.data.model.AlbumData

@Dao
interface AlbumDao {
    @Insert
    fun insert(albumData: AlbumData)

    @Delete
    fun delete(albumData: AlbumData)

    @Update
    fun update(albumData: AlbumData)

    @Query("select * from ${GetConstants.ALBUM_IMG_TABLE}")
    fun getAlbum(): LiveData<List<AlbumData>>

    /*

     */

    @Query("select * from ${GetConstants.ALBUM_IMG_TABLE}  where keyalbum = :key")
    fun getByKey(key: Int):LiveData<List<AlbumData>>


}