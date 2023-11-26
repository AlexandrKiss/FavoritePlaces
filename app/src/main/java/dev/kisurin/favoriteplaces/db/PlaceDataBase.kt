package dev.kisurin.favoriteplaces.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.kisurin.favoriteplaces.db.dao.PlaceDao
import dev.kisurin.favoriteplaces.db.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1, exportSchema = false)
abstract class PlaceDataBase: RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}