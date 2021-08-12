package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.RequestTokenModel
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.RequestTokenService
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestTokenTheMovie(val context: Context) {

    private val mRemote = RetrofitMovies.createService(RequestTokenService::class.java)

    fun requestToken(api_key: String, listener: APIReturn<RequestTokenModel>) {

        val call: Call<RequestTokenModel> = mRemote.requestToken(api_key)

        call.enqueue(object : Callback<RequestTokenModel> {
            override fun onFailure(call: Call<RequestTokenModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<RequestTokenModel>, response: Response<RequestTokenModel>) {
                if (response.code() != MoviesConstants.HTTP.SUCCESS) {
                    try {
                        JSONObject(response.toString())
                        val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
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
