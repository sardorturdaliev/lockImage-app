package com.sardordev.lockimages.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sardordev.lockimages.constants.GetConstants

@Entity(tableName = GetConstants.ALBUM_IMG_TABLE)
data class AlbumData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "albumname")
    val nameAlbum: String = "",
    @ColumnInfo(name = "pathimgbyalbum")
    val path: String = "",
    @ColumnInfo(name = "keyalbum")
    val keyid: Int = 0
)