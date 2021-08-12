/*
DESAFIO SICOOB
Franz Blauth Ximenes
Vitória 12 Agosto de 2021

"Always Pass On What You Have Learned" - Yoda
*/

package com.example.mymovies.ui.popularMovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.R
import com.example.mymovies.data.network.RequestTokenTheMovie
import com.example.mymovies.ui.components.*

class PopularMoviesActivity : AppCompatActivity() {

    private lateinit var requestTokenTheMovie: RequestTokenTheMovie
    private lateinit var requestToken: RequestToken
    private lateinit var createSession: CreateSession
    private lateinit var validationToken: ValidationToken
    private lateinit var getDetails: GetDetails
    private lateinit var popularMovies: PopularMovies
    private lateinit var moviesViewModel: PopularMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movies_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PopularMoviesFragment.newInstance())
                .commitNow()
        }
        supportActionBar?.show()
        moviesViewModel = ViewModelProvider(this).get(PopularMoviesViewModel::class.java)

        requestTokenTheMovie = RequestTokenTheMovie(applicationContext)
        requestToken = RequestToken(application)
        validationToken = ValidationToken(application)
        createSession = CreateSession(application)
        popularMovies = PopularMovies(application)
        getDetails = GetDetails(application)

         observer()
    }

    override fun onResume() {
        super.onResume()
        requestToken.requestToken()
    }

    private fun observer() {

        requestToken.requestTokenOk.observe(this, {
            if (it) {
                validationToken.validationToken()
            }
        })

        validationToken.validationToken.observe(this, {
            if (it) {
                createSession.createSession()
            }
        })

        createSession.saveSession.observe(this, {
            if (it) {
                getDetails.getDetails()
            }
        })

        getDetails.getDetails.observe(this,{
               popularMovies.popularMovies()
        })
    }
}