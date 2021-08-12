package com.example.mymovies.service

import com.example.mymovies.data.model.CreditsModel
import retrofit2.Call
import retrofit2.http.*

interface CreditsService {

    @Headers("Content-Type: application/json")
    @GET("movie/{movie_id}/credits")
    fun creditsMovies(
        @Path(value = "movie_id", encoded = true) movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<CreditsModel>
}