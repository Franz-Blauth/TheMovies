package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class AccountDetailsModel {

        @SerializedName("avatar")
        var avatar: Avatar? = null

        @SerializedName("id")
        var id = 0

        @SerializedName("iso_639_1")
        var iso6391: String? = null

        @SerializedName("iso_3166_1")
        var iso31661: String? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("include_adult")
        var includeAdult = false

        @SerializedName("username")
        var username: String? = null


    inner class Avatar {
        @SerializedName("gravatar")
        var gravatar: Gravatar? = null
    }

    inner class Gravatar {
        @SerializedName("hash")
        var hash: String? = null
    }
}



