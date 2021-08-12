package com.example.mymovies.data.model

import com.google.gson.annotations.SerializedName

class CreditsModel {

    @SerializedName("id")
    var id = 0

    @SerializedName("cast")
    var cast: List<Cast> = arrayListOf()

    @SerializedName("crew")
    var crew: List<Crew> = arrayListOf()

    inner class Cast {

        @SerializedName("adult")
        var adult = false

        @SerializedName("gender")
        var gender = 0

        @SerializedName("id")
        var id = 0

        @SerializedName("known_for_department")
        var knownForDepartment: String = ""

        @SerializedName("name")
        var name: String = ""

        @SerializedName("original_name")
        var originalName: String = ""

        @SerializedName("popularity")
        var popularity = 0.0

        @SerializedName("profile_path")
        var profilePath: String = ""

        @SerializedName("cast_id")
        var castId = 0

        @SerializedName("character")
        var character: String = ""

        @SerializedName("credit_id")
        var creditId: String = ""

        @SerializedName("order")
        var order = 0

    }

    inner class Crew {
        @SerializedName("adult")
        var adult = false

        @SerializedName("gender")
        var gender = 0

        @SerializedName("id")
        var id = 0

        @SerializedName("known_for_department")
        var knownForDepartment: String = ""

        @SerializedName("name")
        var name: String = ""

        @SerializedName("original_name")
        var originalName: String = ""

        @SerializedName("popularity")
        var popularity = 0.0

        @SerializedName("profile_path")
        var profilePath: String = ""

        @SerializedName("credit_id")
        var creditId: String = ""

        @SerializedName("department")
        var department: String = ""

        @SerializedName("job")
        var job: String = ""
    }
}