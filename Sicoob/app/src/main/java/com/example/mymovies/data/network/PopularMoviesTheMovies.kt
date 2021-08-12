package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.PopularMoviesModel
import com.example.mymovies.service.PopularMoviesService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesTheMovies (val context: Context) {

    private val mRemote = RetrofitMovies.createService(PopularMoviesService::class.java)

    fun requestPopularMovies(api_key: String, page: Int, listener: APIReturn<PopularMoviesModel>) {

        val language = MoviesConstants.QUERY.LANGUAGE
        val call: Call<PopularMoviesModel> = mRemote.popularMovies(api_key, language, page)

        call.enqueue(object : Callback<PopularMoviesModel> {
            override fun onFailure(call: Call<PopularMoviesModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<PopularMoviesModel>, response: Response<PopularMoviesModel>) {
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
