package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.FavoriteMoviesModel
import com.example.mymovies.service.FavoriteMoviesService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteMoviesTheMovies (val context: Context) {

    private val mRemote = RetrofitMovies.createService(FavoriteMoviesService::class.java)
    private val sharedPreferences = SharedPreferencesRepository(context)

    fun requestFavoriteMovies(page: Int, listener: APIReturn<FavoriteMoviesModel>) {

        val apiKey = MoviesConstants.QUERY.API_KEY
        val sessionId = sharedPreferences.getShared(MoviesConstants.SHARED.SESSION_ID)
        val accountId = sharedPreferences.getShared(MoviesConstants.SHARED.ACCOUNT_ID).toInt()
        val language = MoviesConstants.QUERY.LANGUAGE

        val call: Call<FavoriteMoviesModel> = mRemote.getFavoriteMovies(accountId, apiKey, sessionId, language, page)

        call.enqueue(object : Callback<FavoriteMoviesModel> {
            override fun onFailure(call: Call<FavoriteMoviesModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<FavoriteMoviesModel>, response: Response<FavoriteMoviesModel>) {
                if (response.code() != MoviesConstants.HTTP.SUCCESS) {
                    try {
                        JSONObject(response.toString())
                        val validation =
                            Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                        listener.onFailure(validation)
                    } catch (ex: JSONException) {
                        listener.onFailure(context.getString(R.string.NO_FAVORITES))
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
