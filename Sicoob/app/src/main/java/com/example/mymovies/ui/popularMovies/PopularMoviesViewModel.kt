package com.example.mymovies.ui.popularMovies

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.*
import com.example.mymovies.data.network.*
import com.example.mymovies.service.retrofit.APIReturn
import com.example.mymovies.ui.clickListener.ListenerModel
import com.example.mymovies.ui.components.MarkAsFavorite

class PopularMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var markFavorite = MarkAsFavorite(application)
    private val popularMoviesTheMovies = PopularMoviesTheMovies(application)
    private val favoriteMoviesTheMovies = FavoriteMoviesTheMovies(application)
    private val requestCreditsMovie = RequestCreditsMovie(application)
    private val getImagesMovies = GetImagesMovies(application)
    private val getVideos = GetVideos(application)

    private val mPopularMovies= MutableLiveData<PopularMoviesModel>()
    var popularMovies: LiveData<PopularMoviesModel> = mPopularMovies

    private val mFavoriteMovies= MutableLiveData<FavoriteMoviesModel>()
    var favoriteMovies: LiveData<FavoriteMoviesModel> = mFavoriteMovies

    private val mImagesModel = MutableLiveData<ImagesModel>()
    var imagesModel : LiveData<ImagesModel> = mImagesModel

    private val mVideosModel = MutableLiveData<VideosModel>()
    var videosModel : LiveData<VideosModel> = mVideosModel

    private val mSicoobVideo = MutableLiveData<Boolean>()
    var sicoobVideo : LiveData<Boolean> = mSicoobVideo

    private val mValueFavorite = MutableLiveData<Boolean>()
    var valueFavorite : LiveData<Boolean> = mValueFavorite


    private val mCreditsModel = MutableLiveData<CreditsModel>()
    var creditsModel : LiveData<CreditsModel> = mCreditsModel


    private val mPopularResult= MutableLiveData<ListenerModel>()

    fun popularMovies(page : Int) {
        popularMoviesTheMovies.requestPopularMovies(
            MoviesConstants.QUERY.API_KEY, page,
            object: APIReturn<PopularMoviesModel> {

                override fun onSuccess(model: PopularMoviesModel) {
                    mPopularMovies.value = model
                }
                override fun onFailure(str: String) {
                    Toast.makeText(getApplication(), R.string.NO_VALID_RESPONSE, Toast.LENGTH_SHORT).show()
                }
        })
    }

    fun favoriteMovies(page : Int) {
        favoriteMoviesTheMovies.requestFavoriteMovies(page, object: APIReturn<FavoriteMoviesModel> {

                override fun onSuccess(model: FavoriteMoviesModel) {
                    mFavoriteMovies.value = model
                }
                override fun onFailure(str: String) {
                    Toast.makeText(getApplication(), R.string.NO_VALID_RESPONSE, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getCreditsMovies(movieId: Int) {
        requestCreditsMovie.getCreditsMovies(movieId,  object: APIReturn<CreditsModel> {

            override fun onSuccess(model: CreditsModel) {
                mCreditsModel.value = model
            }
            override fun onFailure(str: String) {
                Toast.makeText(getApplication(), R.string.NO_VALID_RESPONSE, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getImages(movieId: Int) {
        getImagesMovies.getImagesMovies(movieId,  object: APIReturn<ImagesModel> {

            override fun onSuccess(model: ImagesModel) {
                mImagesModel.value = model
            }
            override fun onFailure(str: String) {
                Toast.makeText(getApplication(), R.string.NO_VALID_RESPONSE, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getVideos(movieId: Int) { getVideos.getVideos(movieId,  object: APIReturn<VideosModel> {
            override fun onSuccess(model: VideosModel) {
                 mSicoobVideo.value = false
                 mVideosModel.value = model
            }

            override fun onFailure(str: String) {
                mSicoobVideo.value = true
            }
        })
    }

    fun saveDetailMovie(model: ListenerModel){
        mPopularResult.value = model
    }

    fun getDetailMovie() : ListenerModel? {
        return mPopularResult.value
    }

    fun setVideoSicoobTrue(){
        mSicoobVideo.value = true
    }

    fun valueFavorite(value: Boolean){
        mValueFavorite.value = value
    }

    fun markFavorite (idMovies : Int){
        markFavorite.markAsFavorite(idMovies, mValueFavorite.value!!)
    }
}
