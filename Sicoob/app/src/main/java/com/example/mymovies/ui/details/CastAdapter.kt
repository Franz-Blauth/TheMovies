package com.example.mymovies.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.data.model.CreditsModel
import com.example.mymovies.ui.clickListener.MovieListener

class CastAdapter : RecyclerView.Adapter<CastViewHolder>() {

    private var list: List<CreditsModel.Cast> = arrayListOf()
    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_cast, parent, false)
        return CastViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun updateList(list: List<CreditsModel.Cast>){
        this.list = list
        notifyDataSetChanged()
    }

}


