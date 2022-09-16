package com.plenart.newfilmofil.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.newfilmofil.api.TMDBApi
import com.plenart.newfilmofil.models.MovieDetails
import com.plenart.newfilmofil.ui.movies.movie_details.MovieDetailsFragmentArgs
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.io.IOException

class MovieDetailsViewModel(val args: MovieDetailsFragmentArgs) : ViewModel(), KoinComponent {

    private val api: TMDBApi by inject()

    var movie: MovieDetails? = null

    suspend fun getMovieDetails(id: Long?): MovieDetails? {
        try {
            Log.i("MOVIE", "inside getmoviedetails in viewmodescope inside trz")
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

}