package com.example.mymovies.service

import com.example.mymovies.data.model.ImagesModel
import com.example.mymovies.data.model.VideosModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface VideosService {

    @Headers("Content-Type: application/json")
    @GET("movie/{movie_id}/videos")
    fun videos(
        @Path(value = "movie_id", encoded = true) movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): Call<VideosModel>
}