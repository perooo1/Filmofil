package com.plenart.newfilmofil.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plenart.newfilmofil.databinding.ItemMovieBinding
import com.plenart.newfilmofil.models.MovieDetails

class MoviesAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = arrayListOf<MovieDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = movies.count()

    fun setMovies(movies: ArrayList<MovieDetails>) {
        this.movies.addAll(movies)
        this.notifyDataSetChanged()
    }
}