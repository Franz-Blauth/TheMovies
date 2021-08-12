package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class ValidationTokenResponseModel {

    @SerializedName("success")
    var success : Boolean = false

    @SerializedName("expires_at")
    var expiresAt :  String = ""

    @SerializedName("request_token")
    var requestToken :  String = ""
}