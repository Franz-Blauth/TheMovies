package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class PopularMoviesModel {

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("results")
    var results: Array<Results> = arrayOf()

    @SerializedName("total_pages")
    var total_pages: Int = 0

    @SerializedName("total_results")
    var total_results: Int = 0

    inner class Results {
        @SerializedName("adult")
        var adult: Boolean = false

        @SerializedName("backdrop_path")
        var backdrop_path: String? = ""

        @SerializedName("genre_ids")
        var genre_ids: Array<Int>? = arrayOf()

        @SerializedName("id")
        var id: Int = 0

        @SerializedName("original_language")
        var original_language: String? = ""

        @SerializedName("original_title")
        var original_title: String? = ""

        @SerializedName("overview")
        var overview: String? = ""

        @SerializedName("popularity")
        var popularity: Float = 0.0F

        @SerializedName("poster_path")
        var poster_path: String? = ""

        @SerializedName("release_date")
        var release_date: String? = ""

        @SerializedName("title")
        var title: String? = ""

        @SerializedName("video")
        var video: Boolean = false

        @SerializedName("vote_average")
        var vote_average: Float = 0.0F

        @SerializedName("vote_count")
        var vote_count: Int = 0
    }
}

