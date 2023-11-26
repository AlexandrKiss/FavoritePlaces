package dev.kisurin.favoriteplaces.data

import java.util.Date

data class Place(
    val id: Int? = null,
    val date: Date,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String?,
    val photoList: List<String>?
)
