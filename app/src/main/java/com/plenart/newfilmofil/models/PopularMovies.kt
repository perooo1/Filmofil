package com.plenart.newfilmofil.models

data class PopularMovies(
    val page: Long,
    val results: ArrayList<MovieDetails>,
    val total_results: Long,
    val total_pages: Long
)