package com.sardordev.lockimages.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sardordev.lockimages.data.model.ImageData
import com.sardordev.lockimages.data.model.SavedImg
import com.sardordev.lockimages.domain.imp.AppRepositoryImp

class GalleryViewModel(private val appRepository: AppRepositoryImp) : ViewModel() {
    val getAllImages: LiveData<List<ImageData>> = appRepository.getAllImages()
    val getIsremember: LiveData<List<ImageData>> = appRepository.getIsremember()
    fun insertImage(imageData: ImageData) {
        imageData.let {
            appRepository.insert(it)
        }
    }

    fun deleteItem() {
        appRepository.delete()
    }

    fun update(imageData: ImageData) {
        appRepository.update(imageData)
    }


    /*
    Save selected
     */


    fun saveSelected(savedImg: SavedImg) {
        appRepository.saveImgSelected(savedImg)
    }


}