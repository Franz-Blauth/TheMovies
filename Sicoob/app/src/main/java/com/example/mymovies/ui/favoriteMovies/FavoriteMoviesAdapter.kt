package com.example.mymovies.ui.favoriteMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.data.model.FavoriteMoviesModel
import com.example.mymovies.ui.clickListener.MovieListener

class FavoriteMoviesAdapter : RecyclerView.Adapter<FavoriteMoviesViewHolder>() {

    private var list: List<FavoriteMoviesModel.Result> = arrayListOf()
    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_movies, parent, false)
        return FavoriteMoviesViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun attachListener(listener: MovieListener) {
        this.listener = listener
    }

    fun updateList(list: List<FavoriteMoviesModel.Result>){
        this.list = list.toList()
        notifyDataSetChanged()
    }

}


