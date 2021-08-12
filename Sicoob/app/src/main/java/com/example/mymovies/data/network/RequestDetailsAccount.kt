package com.example.mymovies.data.network

import android.content.Context
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.AccountDetailsModel
import com.example.mymovies.data.model.PopularMoviesModel
import com.example.mymovies.service.AccountDetailsService
import com.example.mymovies.service.PopularMoviesService
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.service.retrofit.RetrofitMovies
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestDetailsAccount (val context: Context) {

    private val mRemote = RetrofitMovies.createService(AccountDetailsService::class.java)
    private val sharedPreferences = SharedPreferencesRepository(context)

    fun getDetailsAccount(api_key: String, listener: APIReturn<AccountDetailsModel>) {

        val sessionId = sharedPreferences.getShared(MoviesConstants.SHARED.SESSION_ID)
        val call: Call<AccountDetailsModel> = mRemote.accountDetailsService(api_key, sessionId)

        call.enqueue(object : Callback<AccountDetailsModel> {
            override fun onFailure(call: Call<AccountDetailsModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<AccountDetailsModel>, response: Response<AccountDetailsModel>) {
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
