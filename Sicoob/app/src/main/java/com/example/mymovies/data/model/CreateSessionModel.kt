package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class CreateSessionModel {

    @SerializedName("success")
    var success : Boolean = false

    @SerializedName("session_id")
    var sessionId :  String = ""

}