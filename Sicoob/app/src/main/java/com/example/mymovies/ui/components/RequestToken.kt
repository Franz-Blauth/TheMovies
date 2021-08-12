package com.example.mymovies.ui.components

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.PopularMoviesModel
import com.example.mymovies.data.model.RequestTokenModel
import com.example.mymovies.data.network.RequestTokenTheMovie
import com.example.mymovies.service.retrofit.APIReturn

class RequestToken(val application: Application) {

    private val sharedPreferences = SharedPreferencesRepository(application)

    private val requestTokenTheMovie = RequestTokenTheMovie(application)

    private val mRequestToken = MutableLiveData<Boolean>()
    var requestTokenOk: LiveData<Boolean> = mRequestToken

    fun requestToken() {
        requestTokenTheMovie.requestToken(MoviesConstants.QUERY.API_KEY, object : APIReturn<RequestTokenModel> {

            override fun onFailure(str: String) {
                Toast.makeText(application, R.string.NO_TOKEN , Toast.LENGTH_LONG).show()
            }

            override fun onSuccess(model: RequestTokenModel) {
                if (model.requestToken != ""){
                    saveRequestToken(model.requestToken)
                }
            }

        })
    }

    fun saveRequestToken(requestToken: String){
        sharedPreferences.saveShared(MoviesConstants.SHARED.REQUEST_TOKEN, requestToken)
        mRequestToken.value = true
    }


}