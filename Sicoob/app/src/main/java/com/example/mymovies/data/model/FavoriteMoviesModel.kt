package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class FavoriteMoviesModel {

    @SerializedName("page")
    var page = 0

    @SerializedName("results")
    var results: List<Result> = arrayListOf()

    @SerializedName("total_pages")
    var total_pages = 0

    @SerializedName("total_results")
    var total_results = 0


    class Result {
        @SerializedName("adult")
        var adult = false

        @SerializedName("backdrop_path")
        var backdrop_path: Any? = null

        @SerializedName("genre_ids")
        var genre_ids: List<Int>? = null

        @SerializedName("id")
        var id = 0

        @SerializedName("original_language")
        var original_language: String? = null

        @SerializedName("original_title")
        var original_title: String? = null

        @SerializedName("overview")
        var overview: String? = null

        @SerializedName("release_date")
        var release_date: String? = null

        @SerializedName("poster_path")
        var poster_path: String? = null

        @SerializedName("popularity")
        var popularity = 0.0

        @SerializedName("title")
        var title: String? = null

        @SerializedName("video")
        var video = false

        @SerializedName("vote_average")
        var vote_average = 0.0

        @SerializedName("vote_count")
        var vote_count = 0

    }
}