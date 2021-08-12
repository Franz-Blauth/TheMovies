package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class ImagesModel {

    @SerializedName("backdrops")
    var backdrops: List<Backdrop> = arrayListOf()

    @SerializedName("id")
    var id = 0

    @SerializedName("logos")
    var logos: List<Logo> = arrayListOf()

    @SerializedName("posters")
    var posters: List<Poster> = arrayListOf()

    class Backdrop {
        @SerializedName("aspect_ratio")
        var aspect_ratio = 0.0

        @SerializedName("height")
        var height = 0

        @SerializedName("iso_639_1")
        var iso_639_1: String = ""

        @SerializedName("file_path")
        var file_path: String = ""

        @SerializedName("vote_average")
        var vote_average = 0.0

        @SerializedName("vote_count")
        var vote_count = 0

        @SerializedName("width")
        var width = 0

    }

    class Logo {
        @SerializedName("aspect_ratio")
        var aspect_ratio = 0.0

        @SerializedName("height")
        var height = 0

        @SerializedName("iso_639_1")
        var iso_639_1: String = ""

        @SerializedName("file_path")
        var file_path: String = ""

        @SerializedName("vote_average")
        var vote_average = 0.0

        @SerializedName("vote_count")
        var vote_count = 0

        @SerializedName("width")
        var width = 0

    }

    class Poster {
        @SerializedName("aspect_ratio")
        var aspect_ratio = 0.0

        @SerializedName("height")
        var height = 0

        @SerializedName("iso_639_1")
        var iso_639_1: String = ""

        @SerializedName("file_path")
        var file_path: String = ""

        @SerializedName("vote_average")
        var vote_average = 0.0

        @SerializedName("vote_count")
        var vote_count = 0

        @SerializedName("width")
        var width = 0

    }


}