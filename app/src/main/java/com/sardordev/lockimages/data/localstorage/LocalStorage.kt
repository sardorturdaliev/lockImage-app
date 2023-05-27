package com.sardordev.lockimages.data.localstorage

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.drawable.GradientDrawable.Orientation

object LocalStorage {
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    fun init(context: Context) {
        preferences = context.getSharedPreferences("lockPassword", Context.MODE_PRIVATE)
        editor = preferences.edit()
    }


    /*
    save password user
     */
    fun savePassword(password: String): Boolean {
        editor.putString("password", password)
        editor.apply()
        return true
    }


    /*
    get password user
     */
    fun getPassword(): String = preferences.getString("password", "").toString()
    fun saveUserId(id: Int) {
        editor.putInt("userid", id)
        editor.apply()
    }

    /*
    UserExist
     */
    fun getUserId(): Int = preferences.getInt("userid", 0)

    fun firstPin(pin: Int) {
        editor.putInt("pin", pin)
        editor.apply()
    }

    fun getPin(): Int = preferences.getInt("pin", 0)

    /*
    checkPermission
     */
    fun savePermission(check: Boolean) {
        editor.putBoolean("checkP", check)
        editor.apply()
    }

    fun getPermission(): Boolean = preferences.getBoolean("checkP", false)


    fun saveOrientationMain(orientation: Boolean) {
        editor.putBoolean("orientation", orientation)
        editor.apply()
    }


    fun getSavedOrientationMain() = preferences.getBoolean("orientation", false)


    fun saveOrientationAlbum(orientation: Boolean) {
        editor.putBoolean("orientationAlbum", orientation)
        editor.apply()
    }


    fun getSavedOrientationAlbum() = preferences.getBoolean("orientationALbum", false)


}