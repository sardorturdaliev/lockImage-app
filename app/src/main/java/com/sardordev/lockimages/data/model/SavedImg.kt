package com.sardordev.lockimages.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sardordev.lockimages.constants.GetConstants

@Entity(tableName = GetConstants.SAVEDIMG_TABLE)
data class SavedImg(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "path_saved")
    val path: String = "",
    @ColumnInfo(name = "idkey_saved")
    val idkey: Int = 0
)
