package com.example.mymovies.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.data.model.ImagesModel
import com.example.mymovies.ui.clickListener.MovieListener

class ImageAdapter: RecyclerView.Adapter<ImageViewHolder>() {

    private var list: List<ImagesModel.Backdrop> = arrayListOf()
    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_images, parent, false)
        return ImageViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun updateList(list: List<ImagesModel.Backdrop>){
        this.list = list
        notifyDataSetChanged()
    }

}


