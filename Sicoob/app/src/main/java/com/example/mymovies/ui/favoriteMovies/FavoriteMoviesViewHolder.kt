package com.example.mymovies.ui.favoriteMovies

import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.model.FavoriteMoviesModel
import com.example.mymovies.ui.clickListener.ListenerModel
import com.example.mymovies.ui.clickListener.MovieListener
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FavoriteMoviesViewHolder (itemView: View, private val listener: MovieListener)
    : RecyclerView.ViewHolder(itemView) {
    private var ivPosterPath: ImageView = itemView.findViewById(R.id.iv_poster_path)
    private var tableRow: TableRow = itemView.findViewById(R.id.tb_row_movies)
    private var originalTitle: TextView = itemView.findViewById(R.id.original_title)
    private var releaseDate: TextView = itemView.findViewById(R.id.date_release)
    private var voteAverage: TextView = itemView.findViewById(R.id.vote_average)
    private var ivStar1: ImageView = itemView.findViewById(R.id.iv_star_01)
    private var ivStar2: ImageView = itemView.findViewById(R.id.iv_star_02)
    private var ivStar3: ImageView = itemView.findViewById(R.id.iv_star_03)
    private var ivStar4: ImageView = itemView.findViewById(R.id.iv_star_04)
    private var ivStar5: ImageView = itemView.findViewById(R.id.iv_star_05)

    private var listenerModel = ListenerModel()

    fun bindData(model: FavoriteMoviesModel.Result) {


        val average = (model.vote_average * 10).toInt()

        voteAverage.text = (average.toString()) + "%"
        originalTitle.text = model.title
        if (model.release_date.toString() != ""){
            releaseDate.text = convertDate(model.release_date.toString())
        }

        clearStarts()
        defineStars(average)
        downloadPosterPath(model.poster_path)

        listenerModel = ListenerModel()
        listenerModel.apply {
            this.id = model.id
            this.overview = model.overview.toString()
            this.title = model.title.toString()
            this.backdrop_path = model.backdrop_path.toString()
            this.original_title = model.original_title.toString()
        }
        tableRow.setOnClickListener {
            listener.onListClick(listenerModel)
        }
    }

    private fun downloadPosterPath(posterPath: String?){
        val imageUri = MoviesConstants.HTTP.POSTER_PATH + posterPath;
        Picasso.get().load(imageUri).into(ivPosterPath);
    }

    private fun convertDate(dateStr: String) : String{
        try {
            val initDate  = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStr)
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            return formatter.format(initDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun defineStars(average: Int){
        when (average){
            in 1..19 -> {
                ivStar1.setImageResource(R.drawable.ic_star_half)
            }
            in 1..29 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
            }
            in 1..39 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_half)
            }
            in 1..49 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_full)
            }
            in 1..59 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_full)
                ivStar3.setImageResource(R.drawable.ic_star_half)
            }
            in 1..69 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_full)
                ivStar3.setImageResource(R.drawable.ic_star_full)
            }
            in 1..79 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_full)
                ivStar3.setImageResource(R.drawable.ic_star_full)
                ivStar4.setImageResource(R.drawable.ic_star_half)
            }
            in 1..85 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_full)
                ivStar3.setImageResource(R.drawable.ic_star_full)
                ivStar4.setImageResource(R.drawable.ic_star_full)
            }
            in 1..90 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_full)
                ivStar3.setImageResource(R.drawable.ic_star_full)
                ivStar4.setImageResource(R.drawable.ic_star_full)
                ivStar5.setImageResource(R.drawable.ic_star_half)
            }
            in 1..95 -> {
                ivStar1.setImageResource(R.drawable.ic_star_full)
                ivStar2.setImageResource(R.drawable.ic_star_full)
                ivStar3.setImageResource(R.drawable.ic_star_full)
                ivStar4.setImageResource(R.drawable.ic_star_full)
                ivStar5.setImageResource(R.drawable.ic_star_full)
            }
            else -> print("Nothing to do, brother. ")
        }
    }

    private fun clearStarts(){
        ivStar1.setImageResource(R.drawable.ic_star_border)
        ivStar2.setImageResource(R.drawable.ic_star_border)
        ivStar3.setImageResource(R.drawable.ic_star_border)
        ivStar4.setImageResource(R.drawable.ic_star_border)
        ivStar5.setImageResource(R.drawable.ic_star_border)
    }

}
