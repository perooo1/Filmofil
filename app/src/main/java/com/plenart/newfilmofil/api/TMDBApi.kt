package com.plenart.newfilmofil.api

import com.plenart.newfilmofil.models.MovieDetails
import com.plenart.newfilmofil.models.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("/3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String = API_KEY
    ): Response<PopularMovies>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String = API_KEY
    ): Response<MovieDetails>

    @GET("/3/search/movie")
    suspend fun searchForMovie(
        @Query("api_key") api_key: String = API_KEY,
        @Query("query") query: String
    ): Response<PopularMovies>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val API_KEY = "fb407104d1e75c75cc7a13bc3d6cffbb"
    }

}


