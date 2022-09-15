package com.plenart.newfilmofil.api

import com.plenart.newfilmofil.models.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBApi {
    @GET("/3/movie/popular")
    suspend fun getMovies(                                                     // todo suspend fun
        @Query("apikey") api_key: String = API_KEY
    ): Response<PopularMovies>//ArrayList<MovieDetails>                                      ///or response?

    companion object {
        const val BASE_URL = "http://www.omdbapi.com/"
        const val API_KEY = "42874e71"
    }
}