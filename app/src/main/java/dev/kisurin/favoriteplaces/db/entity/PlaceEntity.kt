package dev.kisurin.favoriteplaces.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_places")
data class PlaceEntity (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val date: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String?,
    @ColumnInfo(name = "photo_uri_list") val photoUriList: String?
)