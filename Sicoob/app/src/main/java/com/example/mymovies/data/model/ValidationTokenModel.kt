package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class ValidationTokenModel {

    @SerializedName("username")
    var username : String = ""

    @SerializedName("password")
    var password :  String = ""

    @SerializedName("request_token")
    var requestToken :  String = ""

}