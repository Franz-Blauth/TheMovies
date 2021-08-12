package com.example.mymovies.ui.popularMovies

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.ui.clickListener.ListenerModel
import com.example.mymovies.ui.clickListener.MovieListener
import com.example.mymovies.ui.details.DetailFragment
import com.example.mymovies.ui.favoriteMovies.FavoriteMoviesFragment
import com.example.mymovies.utils.CheckConnection
import com.example.mymovies.utils.DialogMessage

class PopularMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = PopularMoviesFragment()
    }

    private lateinit var listener: MovieListener
    private lateinit var progressBar: ProgressBar
    private lateinit var dialogMessage: DialogMessage
    private lateinit var checkConnection: CheckConnection

    private val adapter = PopularMoviesAdapter()
    private val moviesViewModel: PopularMoviesViewModel by activityViewModels()
    private var numPage : Int = 1
    private var pageMax : Int = 1

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_popular_movies, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_movies)
        recycler.layoutManager = LinearLayoutManager(context)
        progressBar = ProgressBar(context)
        recycler.adapter = adapter
        progressBar = root.findViewById(R.id.movies_progressbar)
        dialogMessage = DialogMessage()
        checkConnection = CheckConnection()

        (activity as AppCompatActivity).supportActionBar?.show()

        listener = object : MovieListener {
            override fun onListClick(model: ListenerModel ) {
                moviesViewModel.saveDetailMovie(model)
                moviesViewModel.valueFavorite(true)
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                if (transaction != null) {
                    transaction.replace(R.id.container, DetailFragment.newInstance())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
        }
        setHasOptionsMenu(true)

        observe()
        return root
    }

    override fun onResume() {
        super.onResume()
        adapter.attachListener(listener)
        loadPopularMovies(numPage)
    }

    private fun observe() {

        moviesViewModel.popularMovies.observe(viewLifecycleOwner, {
            progressBar.visibility = View.GONE;
            pageMax = it.total_pages

            if (it.results.count() > 0) {
                adapter.updateList(it.results)
            } else {
                context?.let { context ->
                    dialogMessage.showMessage(getString(R.string.NO_VALID_RESPONSE), context)
                }
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.page_controls, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_forward -> {
            if (numPage < pageMax){
                numPage += 1
                loadPopularMovies(numPage)
            }
            true
        }
        R.id.action_back -> {
            if (numPage > 1){
                numPage -= 1
                loadPopularMovies(numPage)
            }
            true
        }
        R.id.action_favorite -> {
            listFavoriteMovies()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun loadPopularMovies(numPage: Int){
        if (context?.let { checkConnection.isNetworkAvailable(it) } == true) {
            moviesViewModel.popularMovies(numPage)
        } else {
            context?.let { dialogMessage.showMessage(getString(R.string.NO_NETWORK), it) }
        }
    }

    private fun listFavoriteMovies(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (transaction != null) {
            transaction.replace(R.id.container, FavoriteMoviesFragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}