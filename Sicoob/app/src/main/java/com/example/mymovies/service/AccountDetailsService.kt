package com.example.mymovies.service

import com.example.mymovies.data.model.AccountDetailsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AccountDetailsService {

    @Headers("Content-Type: application/json")
    @GET("account")
    fun accountDetailsService(
        @Query("api_key") api_key: String,
        @Query("session_id") session_id: String,
        ): Call<AccountDetailsModel>

}