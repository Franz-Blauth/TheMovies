package com.example.mymovies.ui.components

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.MarkAsFavoriteModel
import com.example.mymovies.data.network.MarkAsFavoriteMovie
import com.example.mymovies.service.retrofit.APIReturn

class MarkAsFavorite (val application: Application) {

    private val markAsFavoriteMovie = MarkAsFavoriteMovie(application)

    private val mMarkAsFavorite = MutableLiveData<Boolean>()
    var markAsFavorite: LiveData<Boolean> = mMarkAsFavorite

    fun markAsFavorite(movieId: Int, value: Boolean) {
        markAsFavoriteMovie.markAsFavorite(movieId, value , object:
            APIReturn<MarkAsFavoriteModel> {

            override fun onSuccess(model: MarkAsFavoriteModel) {
                mMarkAsFavorite.value = true
            }

            override fun onFailure(str: String) {
                mMarkAsFavorite.value = false
            }
        })
    }
}