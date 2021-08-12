package com.example.mymovies.service

import com.example.mymovies.data.model.AuthenticationModel
import com.example.mymovies.data.model.CreateSessionModel
import retrofit2.Call
import retrofit2.http.*

interface CreateSessionService {

    @Headers("Content-Type: application/json")
    @POST("authentication/session/new")
    fun createSession(
        @Query("api_key") api_key: String,
        @Body authenticationModel: AuthenticationModel): Call<CreateSessionModel>
}
