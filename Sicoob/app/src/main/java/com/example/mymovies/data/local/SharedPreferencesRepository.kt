package com.example.mymovies.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.mymovies.constants.MoviesConstants

class SharedPreferencesRepository(context: Context){

    private val mPreferences: SharedPreferences = context.getSharedPreferences(MoviesConstants.SHARED.MOVIES, Context.MODE_PRIVATE)

    fun saveShared(key: String, value: String) {
        mPreferences.edit().putString(key, value).apply()
    }

    fun getShared(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

    fun removeShared(key: String) {
        mPreferences.edit().remove(key).apply()
    }

    fun allClear() {
        return mPreferences.all.clear()
    }

}
