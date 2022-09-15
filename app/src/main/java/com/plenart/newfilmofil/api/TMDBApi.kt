package com.plenart.newfilmofil.api

import com.plenart.newfilmofil.models.MovieDetails
import com.plenart.newfilmofil.models.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("/3/movie/popular")
    suspend fun getPopular(                                                     // todo suspend fun
        @Query("api_key") api_key: String = API_KEY
    ): Response<PopularMovies>//ArrayList<MovieDetails>                                      ///or response?

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"           //possible .org/3
        const val API_KEY = "fb407104d1e75c75cc7a13bc3d6cffbb"
    }

}