package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class MarkAsFavoriteModel {

    @SerializedName("status_code")
    var statusCode :  Int = 0

    @SerializedName("status_message")
    var statusMessage : String = ""

}