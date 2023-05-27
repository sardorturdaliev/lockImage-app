package com.sardordev.lockimages.data.localstorage.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sardordev.lockimages.constants.GetConstants
import com.sardordev.lockimages.data.model.ImageData

@Dao
interface ImgDao {

    @Insert
    fun insert(imageData: ImageData)

    @Update
    fun undate(imageData: ImageData)


    @Query("delete from ${GetConstants.IMG_TABLE}")
    fun delete()

    @Query("select * from ${GetConstants.IMG_TABLE}")
    fun getAllImages(): LiveData<List<ImageData>>


    @Query("select * from ${GetConstants.IMG_TABLE}  where isremembertable like true")
    fun getIsremember(): LiveData<List<ImageData>>


}