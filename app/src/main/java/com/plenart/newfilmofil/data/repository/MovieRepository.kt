package com.plenart.newfilmofil.data.repository

import com.plenart.newfilmofil.models.MovieDetails

interface MovieRepository {
    fun saveMovie(movie: MovieDetails)
    fun getAllMovies(): List<MovieDetails>
}