package com.plenart.newfilmofil.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plenart.newfilmofil.models.MovieDetails

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieDetails)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieDetails>

}