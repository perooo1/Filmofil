package com.plenart.newfilmofil.ui.movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plenart.newfilmofil.databinding.ItemMovieBinding
import com.plenart.newfilmofil.models.MovieDetails

class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieDetails){
        val posterImgURL = "https://image.tmdb.org/t/p/w342" + movie.posterPath
        binding.apply {
            Glide.with(itemView).load(posterImgURL).into(ivMovieImage)
            tvMovieTitle.text = movie.title
            tvMovieVoteAvg.text = movie.voteAverage.toString()
            ratingBar.numStars = calculateStarRating(movie.voteAverage)

        }
    }

    private fun calculateStarRating(voteAvg: Double): Int{
        return when{
            voteAvg <= 5.0  -> 1
            voteAvg > 5.0 && voteAvg <= 6.5 -> 2
            voteAvg > 6.5 && voteAvg <= 7.5 -> 3
            voteAvg > 7.5 && voteAvg <= 8.4 -> 4
            else -> 5
        }
    }

}