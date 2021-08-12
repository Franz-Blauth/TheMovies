package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.ValidationTokenResponseModel
import com.example.mymovies.data.model.ValidationTokenModel
import com.example.mymovies.service.ValidationTokenService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ValidationTokenTheMovie(val context: Context) {

    private val sharedPreferences = SharedPreferencesRepository(context)
    private val mRemote = RetrofitMovies.createService(ValidationTokenService::class.java)

    fun createSession(api_key: String, listener: APIReturn<ValidationTokenResponseModel>) {

        val requestToken = sharedPreferences.getShared(MoviesConstants.SHARED.REQUEST_TOKEN)
        var validationTokenModel = ValidationTokenModel()
        validationTokenModel.apply {
            this.username = MoviesConstants.QUERY.USER
            this.password = MoviesConstants.QUERY.PASSWORD
            this.requestToken = requestToken
        }
        val call: Call<ValidationTokenResponseModel> = mRemote .createSession(api_key, validationTokenModel)

        call.enqueue(object : Callback<ValidationTokenResponseModel> {
            override fun onFailure(call: Call<ValidationTokenResponseModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<ValidationTokenResponseModel>, response: Response<ValidationTokenResponseModel>) {
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
