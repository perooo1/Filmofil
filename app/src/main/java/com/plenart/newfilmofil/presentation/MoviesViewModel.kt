package com.plenart.newfilmofil.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.newfilmofil.api.TMDBApi
import com.plenart.newfilmofil.models.MovieDetails
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesViewModel : ViewModel(), KoinComponent {

    private val api: TMDBApi by inject()
    private val _movies: MutableLiveData<ArrayList<MovieDetails>> = MutableLiveData()
    private var testMovies: ArrayList<MovieDetails>? = arrayListOf()

    init {
        viewModelScope.launch {
            _movies.value = api.getPopular().body()?.results
            Log.i("TEST", "VIEW MODEL init block after api call in viewmodel")
            Log.i("TEST", "VIEW MODEL init block _movies is: ${_movies.value}")
        }
    }

    val movies: LiveData<ArrayList<MovieDetails>>
        get() = _movies

    suspend fun testFunction(){
        Log.i("TEST", "inside test function")
        val resp = api.getPopular()
        if( resp.isSuccessful && resp.body() != null){
            Log.i("TEST", "resp is success and body non null")
            testMovies = resp.body()?.results
            Log.i("TEST", "${resp.body()}")
        }
        else{
            Log.i("TEST", "Response not success or body == null")
        }

    }


}