package com.sardordev.lockimages.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sardordev.lockimages.data.model.AlbumData
import com.sardordev.lockimages.data.model.SavedImg
import com.sardordev.lockimages.domain.imp.AppRepositoryImp

class AlbumViewModel(private val repositoryImp: AppRepositoryImp) : ViewModel() {

    fun getByidkey(idkey: Int): LiveData<List<SavedImg>> = repositoryImp.getImgById(idkey)


}