package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.ImagesModel
import com.example.mymovies.data.model.VideosModel
import com.example.mymovies.service.ImagesService
import com.example.mymovies.service.VideosService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetVideos (val context: Context) {

    private val mRemote = RetrofitMovies.createService(VideosService::class.java)

    fun getVideos(movieId: Int,  listener: APIReturn<VideosModel>) {

        val apiKey = MoviesConstants.QUERY.API_KEY
        val language = MoviesConstants.QUERY.LANGUAGE
        val call: Call<VideosModel> = mRemote.videos(movieId, apiKey, language)

        call.enqueue(object : Callback<VideosModel> {
            override fun onFailure(call: Call<VideosModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<VideosModel>, response: Response<VideosModel>) {
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
