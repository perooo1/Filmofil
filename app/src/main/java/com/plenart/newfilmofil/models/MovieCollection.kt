package com.plenart.newfilmofil.models

import com.google.gson.annotations.SerializedName

data class MovieCollection(
    val id: Long,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
)