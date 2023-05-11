package com.sardordev.lockimages

import android.app.Application
import com.sardordev.lockimages.data.localstorage.LocalStorage

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
    }
}