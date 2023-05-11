package com.sardordev.lockimages.data.localstorage

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

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
    fun savePassword(password: String) {
        editor.putString("password", password)
        editor.apply()
    }


    /*
    get password user
     */

    fun getPassword(): String = preferences.getString("password", "").toString()


    fun saveUserId(id: Int) {
        editor.putInt("userid", id)
        editor.apply()
    }

    fun getUserId(): Int = preferences.getInt("userid", 0)


}