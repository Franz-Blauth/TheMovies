package com.example.mymovies.constants

class MoviesConstants private constructor() {

    // SharedPreferences
    object SHARED {
        const val MOVIES = "movies"
        const val REQUEST_TOKEN = "request_token"
        const val SESSION_ID = "session_id"
        const val ACCOUNT_ID = "account_id"
        const val PAGE = "page"
        const val FAVORITE = "favorite"
    }

    // Requisições API
    object QUERY {
        const val API_KEY = "49c82daf7e8afda7b65563654f799f48"
        const val USER = "blauth"
        const val PASSWORD = "fb36271809"
        const val LANGUAGE = "pt-BR"
        const val MEDIA_TYPE = "movie"
    }

    object HTTP {
        const val SUCCESS     = 200
        const val BASE_URL    = "https://api.themoviedb.org/3/"
        const val POSTER_PATH = "https://image.tmdb.org/t/p/w500/"
        const val YOUTUBE = "https://www.youtube.com/watch?v="
    }

}