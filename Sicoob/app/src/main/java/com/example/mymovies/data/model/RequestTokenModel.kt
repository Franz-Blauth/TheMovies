package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class RequestTokenModel {

    @SerializedName("success")
    var success : Boolean = false

    @SerializedName("expires_at")
    var expiresAt :  String = ""

    @SerializedName("request_token")
    var requestToken :   String = ""
}