package com.example.mymovies.ui.popularMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.data.model.PopularMoviesModel
import com.example.mymovies.ui.clickListener.MovieListener

class PopularMoviesAdapter  : RecyclerView.Adapter<PopularMoviesViewHolder>() {

    private var list: List<PopularMoviesModel.Results> = arrayListOf()
    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_movies, parent, false)
        return PopularMoviesViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun attachListener(listener: MovieListener) {
        this.listener = listener
    }

    fun updateList(list: Array<PopularMoviesModel.Results>){
        this.list = list.toList()
        notifyDataSetChanged()
    }

}


