package com.example.mymovies.ui.details

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.ImagesModel
import com.squareup.picasso.Picasso

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var ivImage: ImageView = itemView.findViewById(R.id.iv_images)

    fun bindData(model: ImagesModel.Backdrop) {
        downloadPosterPath(model.file_path)
    }

    private fun downloadPosterPath(posterPath: String?){
        val imageUri = MoviesConstants.HTTP.POSTER_PATH + posterPath;
        Picasso.get().load(imageUri).into(ivImage);
    }
}
