package com.plenart.newfilmofil.data.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.plenart.newfilmofil.models.Genre
import com.plenart.newfilmofil.utils.GsonParser

@ProvidedTypeConverter
class GenreConverter {

    private val parser: GsonParser = GsonParser()

    @TypeConverter
    fun toGenreJson(genre: ArrayList<Genre>): String {
        return parser.toJson(genre, object : TypeToken<ArrayList<Genre>>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun fromGenreJson(json: String): ArrayList<Genre> {
        return parser.fromJson<ArrayList<Genre>>(
            json,
            object : TypeToken<ArrayList<Genre>>() {}.type
        ) ?: arrayListOf()
    }


}