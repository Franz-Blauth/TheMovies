package com.example.mymovies.ui.components

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.CreateSessionModel
import com.example.mymovies.data.network.CreateSessionTheMovie
import com.example.mymovies.service.retrofit.APIReturn

class CreateSession (val application: Application) {

    private val sharedPreferences = SharedPreferencesRepository(application)

    private val createSessionTheMovie = CreateSessionTheMovie(application)

    private val mSaveSession = MutableLiveData<Boolean>()
    var saveSession: LiveData<Boolean> = mSaveSession

    fun createSession() {
        createSessionTheMovie.createSession(MoviesConstants.QUERY.API_KEY, object: APIReturn<CreateSessionModel> {

            override fun onSuccess(model: CreateSessionModel) {
                saveSessionId(model.sessionId)
            }

            override fun onFailure(str: String) {
            }
        })
    }

    fun saveSessionId(sessionId: String){
        sharedPreferences.saveShared(MoviesConstants.SHARED.SESSION_ID, sessionId)
        mSaveSession.value = true
    }

}