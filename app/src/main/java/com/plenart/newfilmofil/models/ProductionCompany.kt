package com.plenart.newfilmofil.models

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val name: String,
    val id: Long,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("origin_country")
    val originCountry: String
)