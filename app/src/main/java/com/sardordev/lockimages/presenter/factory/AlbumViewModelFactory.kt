package com.sardordev.lockimages.presenter.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sardordev.lockimages.domain.imp.AppRepositoryImp
import com.sardordev.lockimages.viewmodel.AlbumViewModel

class AlbumViewModelFactory(private val repositoryImp: AppRepositoryImp) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
            return AlbumViewModel(repositoryImp) as T
        }
        throw IllegalAccessException("error")
    }
}