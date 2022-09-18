package com.plenart.newfilmofil.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.plenart.newfilmofil.api.TMDBApi
import com.plenart.newfilmofil.data.repository.MovieRepository
import com.plenart.newfilmofil.models.MovieDetails
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.io.IOException

class MovieDetailsViewModel(val repo: MovieRepository) :
    ViewModel(), KoinComponent {

    private val api: TMDBApi by inject()
    var movie: MovieDetails? = null

    suspend fun getMovieDetails(id: Long?): MovieDetails? {
        try {
            id?.let {
                movie = api.getMovieDetails(id.toString()).body()!!
            }
        } catch (e: IOException) {
            Log.e("TAG", e.printStackTrace().toString())
        } catch (e: HttpException) {
            Log.e("TAG", e.printStackTrace().toString())
        }

        return movie

    }

    fun saveMovie(movie: MovieDetails) {
        repo.saveMovie(movie)
    }

}