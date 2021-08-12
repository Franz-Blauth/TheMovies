package com.example.mymovies.service

import com.example.mymovies.data.model.PopularMoviesModel
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface PopularMoviesService {

    @Headers("Content-Type: application/json")
    @POST("movie/popular")
    fun popularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularMoviesModel>
}