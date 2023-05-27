package com.sardordev.lockimages.app

import android.app.Application
import com.sardordev.lockimages.data.localstorage.LocalStorage
import com.sardordev.lockimages.data.localstorage.db.DatabaseImg
import com.sardordev.lockimages.domain.imp.AppRepositoryImp

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseImg.init(this)
        AppRepositoryImp.init(this)
        LocalStorage.init(this)
    }
}