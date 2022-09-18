package com.plenart.newfilmofil.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plenart.newfilmofil.data.repository.MovieRepository
import com.plenart.newfilmofil.models.MovieDetails

class WatchlistViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movies: MutableLiveData<ArrayList<MovieDetails>> = MutableLiveData()

    val movies: LiveData<ArrayList<MovieDetails>>
        get() = _movies


    init{
        _movies.value = ArrayList(repository.getAllMovies())
    }

}