package com.example.mymovies.service.retrofit

import com.example.mymovies.data.model.PopularMoviesModel

interface APIReturn <T> {
    fun onSuccess(model: T)
    fun onFailure(str: String)
}