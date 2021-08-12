package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.CreditsModel
import com.example.mymovies.data.model.ImagesModel
import com.example.mymovies.service.CreditsService
import com.example.mymovies.service.ImagesService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetImagesMovies (val context: Context) {

    private val mRemote = RetrofitMovies.createService(ImagesService::class.java)

    fun getImagesMovies(movieId: Int,  listener: APIReturn<ImagesModel>) {

        val apiKey = MoviesConstants.QUERY.API_KEY
        val call: Call<ImagesModel> = mRemote.imagesMovies(movieId, apiKey)

        call.enqueue(object : Callback<ImagesModel> {
            override fun onFailure(call: Call<ImagesModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<ImagesModel>, response: Response<ImagesModel>) {
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
