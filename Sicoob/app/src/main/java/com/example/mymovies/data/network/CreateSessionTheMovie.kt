package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.AuthenticationModel
import com.example.mymovies.data.model.CreateSessionModel
import com.example.mymovies.service.CreateSessionService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateSessionTheMovie(val context: Context) {

    private val sharedPreferences = SharedPreferencesRepository(context)
    private val mRemote = RetrofitMovies.createService(CreateSessionService::class.java)

    fun createSession(api_key: String, listener: APIReturn<CreateSessionModel>) {

        val authenticationModel = AuthenticationModel()
        authenticationModel.apply {
            this.requestToken = sharedPreferences.getShared(MoviesConstants.SHARED.REQUEST_TOKEN)
        }
        val call: Call<CreateSessionModel> = mRemote.createSession(api_key, authenticationModel)

        call.enqueue(object : Callback<CreateSessionModel> {
            override fun onFailure(call: Call<CreateSessionModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<CreateSessionModel>, response: Response<CreateSessionModel>) {
                if (response.code() != MoviesConstants.HTTP.SUCCESS) {
                    try {
                        JSONObject(response.toString())
                        val validation =
                            Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                        listener.onFailure(validation)
                    } catch (ex: JSONException) {
                        listener.onFailure(context.getString(R.string.NO_VALID_RESPONSE))
                        return
                    }

                } else {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }

                }
            }
        })
    }
}
