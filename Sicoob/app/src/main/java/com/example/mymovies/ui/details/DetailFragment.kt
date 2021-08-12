package com.example.mymovies.ui.details

import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.ui.popularMovies.PopularMoviesViewModel
import com.example.mymovies.utils.DialogMessage
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val popularMoviesViewModel: PopularMoviesViewModel by activityViewModels()
    private val dialogMessage = DialogMessage()
    private lateinit var backdropPath: ImageView
    private lateinit var overview : TextView
    private lateinit var originalTitle: TextView
    private lateinit var title: TextView
    private lateinit var totalCast: TextView
    private val adapterCast = CastAdapter()
    private val adapterImage = ImageAdapter()
    private lateinit var webView: WebView
    private var movieId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        val recyclerCast = root.findViewById<RecyclerView>(R.id.recycler_cast)
        recyclerCast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerCast.adapter = adapterCast

        val recyclerImage = root.findViewById<RecyclerView>(R.id.recycler_images)
        recyclerImage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerImage.adapter = adapterImage

        webView = root.findViewById(R.id.webview) as WebView
        webView.webViewClient = WebViewClient()

        val webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true

        backdropPath = root.findViewById(R.id.backdropPath)
        overview  = root.findViewById(R.id.overview)
        originalTitle = root.findViewById(R.id.original_title)
        totalCast = root.findViewById(R.id.total_cast)
        title = root.findViewById(R.id.title)

        setHasOptionsMenu(true)

        observe()
        return root
    }

    override fun onResume() {
        super.onResume()
        val model = popularMoviesViewModel.getDetailMovie()
        if (model != null) {
            movieId = model.id
            getDetailMovie(model.id)
            downloadPosterPath(model.backdrop_path)
            title.text = model.title
            overview.text = model.overview
            originalTitle.text = model.original_title
        }
    }

    private fun observe(){
        popularMoviesViewModel.creditsModel.observe(viewLifecycleOwner,{
                totalCast.text = (it.cast.count()).toString()
                adapterCast.updateList(it.cast)
                getImagesMovies(movieId)
        })

        popularMoviesViewModel.imagesModel.observe(viewLifecycleOwner, {
            adapterImage.updateList(it.backdrops)
            getVideos(movieId)
        })

        popularMoviesViewModel.videosModel.observe(viewLifecycleOwner, {
            if (it.results.count() > 0) {
                if (popularMoviesViewModel.sicoobVideo.value == true) {
                    playVideo("nVKxSJZndYU")
                } else {
                    playVideo(it.results[0].key)
                }
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

            inflater.inflate(R.menu.favorite_true, menu)
            super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            setMovieFavorite(movieId)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun downloadPosterPath(posterPath: String?){
        val imageUri = MoviesConstants.HTTP.POSTER_PATH + posterPath;
        Picasso.get().load(imageUri).into(backdropPath);
    }

    private fun getDetailMovie(movieId: Int){
        popularMoviesViewModel.getCreditsMovies(movieId)
    }

    private fun getImagesMovies(movieId: Int){
        popularMoviesViewModel.getImages(movieId)
    }

    private fun getVideos(movieId: Int){
        popularMoviesViewModel.getVideos(movieId)
    }

    private fun playVideo(key: String){
        val url: String = MoviesConstants.HTTP.YOUTUBE + key
        webView.loadUrl(url)
        popularMoviesViewModel.setVideoSicoobTrue()
    }

    private fun setMovieFavorite(movieId: Int){
        popularMoviesViewModel.markFavorite(movieId)
        if (popularMoviesViewModel.valueFavorite.value!!) {
            context?.let { dialogMessage.showMessage(getString(R.string.MARK), it) }
        }else{
            context?.let { dialogMessage.showMessage(getString(R.string.UNMARK), it) }
        }
    }
}