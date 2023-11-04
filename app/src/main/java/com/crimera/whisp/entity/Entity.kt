package com.crimera.whisp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("workno")
    val workno: String = "",
    @SerialName("work_name")
    val workName: String = "",
    @SerialName("image_main")
    val imageMain: ImageMain? = null,
    @SerialName("genres")
    val genres: List<Genre>? = null,
    @SerialName("age_category")
    val ageCategory: Int? = null,
    @SerialName("maker_name_en")
    val makerName: String = ""
)

@Serializable
data class Genre(
    @SerialName("name")
    val name: String?
)

@Serializable
data class ImageMain(
    @SerialName("url")
    val url: String?
)

@Serializable
data class AsmrOne(
    @SerialName("error")
    val error: String? = null
)