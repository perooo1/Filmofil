package com.plenart.newfilmofil.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieDetails(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    //@SerializedName("belongs_to_collection")
    //@Ignore val movieCollection: MovieCollection?,                               // as in LOTR, HP, Die hard,.. franchises in general
    val budget: Int,
    //@Ignore val genres: ArrayList<Genre>,
    val homepage: String?,
    @PrimaryKey
    val id: Long,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    //@SerializedName("production_companies")
    //@Ignore val productionCompanies: ArrayList<ProductionCompany>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,                                                   //some fields can be null, probably put ?
    val status: String,                                                 //rumored/planned/in production/post production/released/canceled
    val tagline: String?,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)