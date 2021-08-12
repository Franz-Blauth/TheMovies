package com.example.mymovies.ui.favoriteMovies

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
import com.example.mymovies.ui.popularMovies.PopularMoviesViewModel
import com.example.mymovies.utils.CheckConnection
import com.example.mymovies.utils.DialogMessage

class FavoriteMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteMoviesFragment()
    }

    private lateinit var listener: MovieListener
    private lateinit var progressBar: ProgressBar
    private lateinit var dialogMessage: DialogMessage
    private lateinit var checkConnection: CheckConnection

    private val adapter = FavoriteMoviesAdapter()
    private val moviesViewModel: PopularMoviesViewModel by activityViewModels()
    private var numPage: Int = 1
    private var pageMax: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorite_movies, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_favorites)
        recycler.layoutManager = LinearLayoutManager(context)
        progressBar = ProgressBar(context)
        recycler.adapter = adapter
        progressBar = root.findViewById(R.id.favorite_progressbar)
        dialogMessage = DialogMessage()
        checkConnection = CheckConnection()

        (activity as AppCompatActivity).supportActionBar?.show()

        listener = object : MovieListener {
            override fun onListClick(model: ListenerModel) {
                moviesViewModel.saveDetailMovie(model)
                moviesViewModel.valueFavorite(false)
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
        loadFavoriteMovies(numPage)
    }

    private fun observe() {

        moviesViewModel.favoriteMovies.observe(viewLifecycleOwner, {
            progressBar.visibility = View.GONE;
            pageMax = it.total_pages

            if (it.results.count() > 0) {
                adapter.updateList(it.results)
            } else {
                context?.let { context ->
                    dialogMessage.showMessage(getString(R.string.NO_FAVORITES), context)
                    activity?.supportFragmentManager?.popBackStack()
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
            if (numPage < pageMax) {
                numPage += 1
                loadFavoriteMovies(numPage)
            }
            true
        }
        R.id.action_back -> {
            if (numPage > 1) {
                numPage -= 1
                loadFavoriteMovies(numPage)
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun loadFavoriteMovies(numPage: Int) {
        if (context?.let { checkConnection.isNetworkAvailable(it) } == true) {
            moviesViewModel.favoriteMovies(numPage)
        } else {
            context?.let { dialogMessage.showMessage(getString(R.string.NO_NETWORK), it) }
        }
    }
}