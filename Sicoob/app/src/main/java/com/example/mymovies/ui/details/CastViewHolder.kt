package com.example.mymovies.ui.details

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.CreditsModel
import com.squareup.picasso.Picasso

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var ivPosterCast: ImageView = itemView.findViewById(R.id.iv_poster_cast)
    private var name: TextView = itemView.findViewById(R.id.name)

    fun bindData(model: CreditsModel.Cast) {
        name.text = model.name
        downloadPosterPath(model.profilePath)
    }

    private fun downloadPosterPath(posterPath: String?){
        val imageUri = MoviesConstants.HTTP.POSTER_PATH + posterPath;
        Picasso.get().load(imageUri).into(ivPosterCast);
    }
}
