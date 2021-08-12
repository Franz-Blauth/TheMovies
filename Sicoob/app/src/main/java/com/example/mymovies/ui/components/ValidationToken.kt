package com.example.mymovies.ui.components

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.PopularMoviesModel
import com.example.mymovies.data.model.ValidationTokenResponseModel
import com.example.mymovies.data.network.ValidationTokenTheMovie
import com.example.mymovies.service.retrofit.APIReturn

class ValidationToken  (val application: Application) {

    private val sharedPreferences = SharedPreferencesRepository(application)

    private val createSessionTheMovie = ValidationTokenTheMovie(application)

    private val mValidationToken = MutableLiveData<Boolean>()
    var validationToken: LiveData<Boolean> = mValidationToken

    fun validationToken() {
        createSessionTheMovie.createSession(MoviesConstants.QUERY.API_KEY, object: APIReturn<ValidationTokenResponseModel> {

            override fun onSuccess(model: ValidationTokenResponseModel) {
                saveSessionId(model.requestToken)
            }

            override fun onFailure(str: String) {
            }

        })
    }

    fun saveSessionId(sessionId: String){
        sharedPreferences.saveShared(MoviesConstants.SHARED.REQUEST_TOKEN, sessionId)
        mValidationToken.value = true
    }

}