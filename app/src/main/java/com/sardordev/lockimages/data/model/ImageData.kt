package com.sardordev.lockimages.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sardordev.lockimages.constants.GetConstants

@Entity(tableName = GetConstants.IMG_TABLE)
data class ImageData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "pathtable")
    val path: String,
    @ColumnInfo(name = "isremembertable")
    var isRemember: Boolean = false
)
