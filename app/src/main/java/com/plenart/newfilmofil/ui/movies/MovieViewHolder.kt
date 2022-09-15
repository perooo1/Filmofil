package com.plenart.newfilmofil.ui.movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plenart.newfilmofil.databinding.ItemMovieBinding
import com.plenart.newfilmofil.models.MovieDetails
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieDetails){
        binding.apply {
            //Picasso.get().load(movie.backdropPath).into(ivMovieImage)ž
            Glide.with(itemView).load(movie.posterPath).into(ivMovieImage)                //potential error due to itemView
            tvMovieTitle.text = movie.title
            tvMovieYearReleased.text = movie.releaseDate

        }
    }

}