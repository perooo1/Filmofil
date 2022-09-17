package com.plenart.newfilmofil.data.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.plenart.newfilmofil.models.MovieCollection
import com.plenart.newfilmofil.utils.GsonParser

@ProvidedTypeConverter
class MovieCollectionConverter {
    private val parser: GsonParser = GsonParser()

    @TypeConverter
    fun toMovieCollectionJson(movieCollection: MovieCollection): String {
        return parser.toJson(movieCollection, object : TypeToken<MovieCollection>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun fromMovieCollectionJson(json: String): MovieCollection? {
        return parser.fromJson<MovieCollection>(json, object : TypeToken<MovieCollection>(){}.type) ?: null
    }
}