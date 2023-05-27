package com.sardordev.lockimages.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sardordev.lockimages.data.model.AlbumData
import com.sardordev.lockimages.domain.imp.AppRepositoryImp

class MainViewModel(private val repositoryImp: AppRepositoryImp) : ViewModel() {
    val getAlbum: LiveData<List<AlbumData>> = repositoryImp.getAlbum()
    fun insertAlbum(albumData: AlbumData) {
        repositoryImp.createAlbum(albumData)
    }
}