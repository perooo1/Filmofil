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
    private val _searchResults: MutableLiveData<ArrayList<MovieDetails>> = MutableLiveData()

    val movies: LiveData<ArrayList<MovieDetails>>
        get() = _movies

    val searchResults: LiveData<ArrayList<MovieDetails>>
        get() = _searchResults

    init {
        viewModelScope.launch {
            _movies.value = api.getPopular().body()?.results
        }
    }

    suspend fun searchForMovie(query: String){
        val resp = api.searchForMovie(TMDBApi.API_KEY, query)
        if( resp.isSuccessful && resp.body() != null){
            _searchResults.value = resp.body()?.results
        }
        else{
            Log.i("TEST", "search Response not success or body == null")
        }


    }

    fun clearSearchedMovie(){
        this._searchResults.value?.clear()
    }

}

