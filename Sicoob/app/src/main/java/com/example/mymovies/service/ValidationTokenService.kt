package com.example.mymovies.service

import com.example.mymovies.data.model.ValidationTokenResponseModel
import com.example.mymovies.data.model.ValidationTokenModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ValidationTokenService {

    @Headers("Content-Type: application/json")
    @POST("authentication/token/validate_with_login")
    fun createSession(
        @Query("api_key") api_key: String,
        @Body validationToken: ValidationTokenModel): Call<ValidationTokenResponseModel>
}
