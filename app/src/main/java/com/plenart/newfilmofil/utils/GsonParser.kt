package com.plenart.newfilmofil.utils

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser: JsonParser {

    private val gson: Gson = Gson()

    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String {
        return gson.toJson(obj, type)
    }
}