package com.diegogutierrez.seventhart.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegogutierrez.domain.Movie
import com.diegogutierrez.seventhart.R
import com.diegogutierrez.seventhart.ui.common.basicDiffUtil
import com.diegogutierrez.seventhart.ui.common.inflate
import com.diegogutierrez.seventhart.ui.common.loadUrl
import kotlinx.android.synthetic.main.view_movie.view.*

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var movies: List<Movie> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.tmdbId == new.tmdbId }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_movie, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.movieTitle.text = movie.title
            val moviePoster = if (movie.posterPath != "") {
                "https://image.tmdb.org/t/p/w185/${movie.posterPath}"
            } else {
                "https://i.ibb.co/Lt37RWS/7th-art-logo-copy.png"
            }

            itemView.movieCover.loadUrl(moviePoster)
        }
    }
}