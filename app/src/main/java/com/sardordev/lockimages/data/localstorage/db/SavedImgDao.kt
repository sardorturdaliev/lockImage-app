package com.sardordev.lockimages.data.localstorage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sardordev.lockimages.constants.GetConstants
import com.sardordev.lockimages.data.model.SavedImg

@Dao
interface SavedImgDao {

    @Insert
    fun insert(savedImg: SavedImg)

    @Update
    fun update(savedImg: SavedImg)

    @Query("select * from ${GetConstants.SAVEDIMG_TABLE}")
    fun getSavedImg(): LiveData<List<SavedImg>>

    @Query("select * from ${GetConstants.SAVEDIMG_TABLE}  where idkey_saved = :idkey")
    fun getDataById(idkey: Int): LiveData<List<SavedImg>>

}