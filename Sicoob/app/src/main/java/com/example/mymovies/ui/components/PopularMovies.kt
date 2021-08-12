package com.example.mymovies.ui.components

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.PopularMoviesModel
import com.example.mymovies.data.network.PopularMoviesTheMovies
import com.example.mymovies.service.retrofit.APIReturn

class PopularMovies  (val application: Application) {


    private val popularMoviesTheMovies = PopularMoviesTheMovies(application)

    private val mPopularMovies= MutableLiveData<PopularMoviesModel>()
    var popularMovies: LiveData<PopularMoviesModel> = mPopularMovies

    fun popularMovies() {
        popularMoviesTheMovies.requestPopularMovies(MoviesConstants.QUERY.API_KEY, 1,
            object:  APIReturn<PopularMoviesModel> {

            override fun onSuccess(model: PopularMoviesModel) {
                mPopularMovies.value = model
            }
            override fun onFailure(str: String) {
            }
        })
    }
}