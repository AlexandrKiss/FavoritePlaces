package dev.kisurin.favoriteplaces.repository.mapper

import android.icu.util.Calendar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.kisurin.favoriteplaces.data.Place
import dev.kisurin.favoriteplaces.db.entity.PlaceEntity
import java.lang.reflect.Type

fun Place.toEntity() = PlaceEntity(
    date = date.time,
    name = name,
    latitude = latitude,
    longitude = longitude,
    description = description,
    photoUriList = photoList?.toJson()
)

fun PlaceEntity.toDto() = Place(
    id = id ?: 0,
    date = Calendar.getInstance().also { it.timeInMillis = date }.time,
    name = name,
    latitude = latitude,
    longitude = longitude,
    description = description,
    photoList = photoUriList?.fromJson()
)

fun List<String>.toJson(): String {
    return Gson().toJson(this)
}

fun String.fromJson(): List<String> {
    val listType: Type = object : TypeToken<List<String>>(){}.type
    return Gson().fromJson(this, listType)
}