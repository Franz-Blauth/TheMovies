package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class RequestMarkAsFavoriteModel {
    @SerializedName("media_type")
    var mediaType : String = ""

    @SerializedName("media_id")
    var mediaId :  Int = 0

    @SerializedName("favorite")
    var favorite :  Boolean = true
}