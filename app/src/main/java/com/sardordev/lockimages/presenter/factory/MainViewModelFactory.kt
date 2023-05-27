package com.sardordev.lockimages.presenter.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sardordev.lockimages.domain.imp.AppRepositoryImp
import com.sardordev.lockimages.viewmodel.MainViewModel

class MainViewModelFactory(private val repositoryImp: AppRepositoryImp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repositoryImp) as T
        }
        throw IllegalAccessException("error")
    }
}