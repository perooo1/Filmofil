package com.plenart.newfilmofil.ui.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plenart.newfilmofil.databinding.ItemMovieBinding
import com.plenart.newfilmofil.models.MovieDetails
import com.plenart.newfilmofil.ui.movies.MovieViewHolder
import com.plenart.newfilmofil.ui.movies.OnMovieSelectedListener

class WatchlistAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = arrayListOf<MovieDetails>()
    var onMovieSelectedListener: OnMovieSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        onMovieSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onMovieSelected(movie.id) }
        }
    }

    override fun getItemCount(): Int = movies.count()

    fun setMoviesWatchlist(movies: ArrayList<MovieDetails>) {
        this.movies.clear()
        this.movies.addAll(movies)
        this.notifyDataSetChanged()
    }
}