package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.AccountDetailsModel
import com.example.mymovies.data.model.CreditsModel
import com.example.mymovies.data.model.PopularMoviesModel
import com.example.mymovies.service.AccountDetailsService
import com.example.mymovies.service.CreditsService
import com.example.mymovies.service.PopularMoviesService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestCreditsMovie (val context: Context) {

    private val mRemote = RetrofitMovies.createService(CreditsService::class.java)

    fun getCreditsMovies(movieId: Int,  listener: APIReturn<CreditsModel>) {

        val apiKey = MoviesConstants.QUERY.API_KEY
        val call: Call<CreditsModel> = mRemote.creditsMovies(movieId, apiKey)

        call.enqueue(object : Callback<CreditsModel> {
            override fun onFailure(call: Call<CreditsModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<CreditsModel>, response: Response<CreditsModel>) {
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
