package com.plenart.newfilmofil.data.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.plenart.newfilmofil.models.ProductionCompany
import com.plenart.newfilmofil.utils.GsonParser

@ProvidedTypeConverter
class ProductionCompanyConverter {

    private val parser: GsonParser = GsonParser()

    @TypeConverter
    fun toProductionCompanyJson(productionCompany: ArrayList<ProductionCompany>): String {
        return parser.toJson(productionCompany, object : TypeToken<ArrayList<ProductionCompany>>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun toProductionCompanyJson(json: String): ArrayList<ProductionCompany> {
        return parser.fromJson<ArrayList<ProductionCompany>>(
            json,
            object : TypeToken<ArrayList<ProductionCompany>>() {}.type
        ) ?: arrayListOf()
    }
}