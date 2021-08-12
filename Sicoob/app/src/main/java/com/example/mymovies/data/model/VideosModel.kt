package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

class VideosModel {

    @SerializedName("id")
    var id = 0

    @SerializedName("results")
    var results: List<Result> = arrayListOf()

    class Result {

        @SerializedName("iso_639_1")
        var iso_639_1: String = ""

        @SerializedName("iso_3166_1")
        var iso_3166_1: String = ""

        @SerializedName("name")
        var name: String = ""

        @SerializedName("key")
        var key: String = ""

        @SerializedName("site")
        var site: String = ""

        @SerializedName("size")
        var size = 0

        @SerializedName("type")
        var type: String = ""

        @SerializedName("official")
        var official = false

        @SerializedName("published_at")
        var published_at: String =
            ""
        @SerializedName("id")
        var id: String = ""
    }


}