package com.example.mymovies.service

import com.example.mymovies.data.model.MarkAsFavoriteModel
import com.example.mymovies.data.model.RequestMarkAsFavoriteModel
import retrofit2.Call
import retrofit2.http.*

interface MarkAsFavoriteService {

    @Headers("Content-Type: application/json")
    @POST("account/{account_id}/favorite")
    fun markAsFavorite(
        @Path(value = "account_id", encoded = true) account_id: Int,
        @Query("api_key") api_key: String,
        @Query("session_id") session_id: String,
        @Body  requestMarkAsFavoriteModel : RequestMarkAsFavoriteModel): Call<MarkAsFavoriteModel>
}