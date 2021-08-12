package com.example.mymovies.service

import com.example.mymovies.data.model.FavoriteMoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FavoriteMoviesService {

    @Headers("Content-Type: application/json")
    @GET("account/{account_id}/favorite/movies")
    fun getFavoriteMovies(
        @Path(value = "account_id", encoded = true) account_id: Int,
        @Query("api_key") api_key: String,
        @Query("session_id")session_id : String,
        @Query("language") language: String,
        @Query("page") page: Int
            ): Call<FavoriteMoviesModel>
}