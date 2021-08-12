package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.AuthenticationModel
import com.example.mymovies.data.model.CreateSessionModel
import com.example.mymovies.data.model.MarkAsFavoriteModel
import com.example.mymovies.data.model.RequestMarkAsFavoriteModel
import com.example.mymovies.service.MarkAsFavoriteService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarkAsFavoriteMovie (val context: Context) {

    private val sharedPreferences = SharedPreferencesRepository(context)
    private val mRemote = RetrofitMovies.createService(MarkAsFavoriteService::class.java)

    fun markAsFavorite(media_id: Int, value: Boolean,  listener: APIReturn<MarkAsFavoriteModel>) {

        val apiKey = MoviesConstants.QUERY.API_KEY
        val accountId = sharedPreferences.getShared(MoviesConstants.SHARED.ACCOUNT_ID).toInt()
        val sessionId = sharedPreferences.getShared(MoviesConstants.SHARED.SESSION_ID)

        val requestMarkAsFavoriteModel = RequestMarkAsFavoriteModel()
        requestMarkAsFavoriteModel.apply {
                this.mediaId = media_id
                this.mediaType = MoviesConstants.QUERY.MEDIA_TYPE
                this.favorite = value
        }

        val call: Call<MarkAsFavoriteModel> = mRemote.markAsFavorite(accountId, apiKey, sessionId, requestMarkAsFavoriteModel )

        call.enqueue(object : Callback<MarkAsFavoriteModel> {
            override fun onFailure(call: Call<MarkAsFavoriteModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<MarkAsFavoriteModel>, response: Response<MarkAsFavoriteModel>) {
                if (response.code() != MoviesConstants.HTTP.SUCCESS && response.code() != 201) {
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
