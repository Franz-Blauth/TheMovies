package com.example.mymovies.service

import com.example.mymovies.data.model.RequestTokenModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RequestTokenService {

    @Headers("Content-Type: application/json")
    @GET("authentication/token/new")
    fun requestToken( @Query("api_key") api_key: String): Call<RequestTokenModel>

}