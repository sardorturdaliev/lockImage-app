package com.sardordev.lockimages.presenter.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sardordev.lockimages.domain.imp.AppRepositoryImp
import com.sardordev.lockimages.viewmodel.GalleryViewModel

class GalleryViewModelFactory(private val repository: AppRepositoryImp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            return GalleryViewModel(repository) as T
        }
        throw IllegalAccessException("error")
    }
}