package com.plenart.newfilmofil.data.repository

import com.plenart.newfilmofil.data.MovieDao
import com.plenart.newfilmofil.models.MovieDetails

class MovieRepositoryImpl(private val dao : MovieDao) : MovieRepository {
    override fun saveMovie(movie: MovieDetails) {
        dao.saveMovie(movie)
    }

    override fun getAllMovies(): List<MovieDetails> {
        return dao.getAllMovies()
    }
}