package com.plenart.newfilmofil.ui.movies

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plenart.newfilmofil.databinding.ItemMovieBinding
import com.plenart.newfilmofil.models.MovieDetails

class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieDetails){
        val imgURL = "https://image.tmdb.org/t/p/w342" + movie.posterPath


        binding.apply {
            Log.i("TEST", "$imgURL")
            Glide.with(itemView).load(imgURL).into(ivMovieImage)
            //Glide.with(itemView).load(movie.posterPath).into(ivMovieImage)                //potential error due to itemView
            tvMovieTitle.text = movie.title
            tvMovieYearReleased.text = movie.releaseDate


        }
    }

}