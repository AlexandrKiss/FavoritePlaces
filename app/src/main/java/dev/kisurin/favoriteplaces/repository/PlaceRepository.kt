package dev.kisurin.favoriteplaces.repository

import dev.kisurin.favoriteplaces.data.Place

interface PlaceRepository {
    fun add(place: Place): Long
    fun update(place: Place)
    fun delete(place: Place)
    fun deleteById(id: Long)
    fun getItemById(id: Long): Place
    fun getItemByPosition(latitude: Double, longitude: Double): Place
    fun getAll(): List<Place>
}