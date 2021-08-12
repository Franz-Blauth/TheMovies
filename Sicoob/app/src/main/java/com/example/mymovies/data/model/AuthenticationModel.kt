package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class AuthenticationModel {

    @SerializedName("request_token")
    var requestToken : String = ""

}