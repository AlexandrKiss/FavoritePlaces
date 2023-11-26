package dev.kisurin.favoriteplaces.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.kisurin.favoriteplaces.db.entity.PlaceEntity

@Dao
interface PlaceDao {
    @Insert
    fun add(place: PlaceEntity): Long

    @Update
    fun update(place: PlaceEntity)

    @Delete
    fun delete(place: PlaceEntity)

    @Query("DELETE FROM favorite_places WHERE id=:id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM favorite_places WHERE id=:id")
    fun getItemById(id: Long): PlaceEntity

    @Query("SELECT * FROM favorite_places WHERE latitude=:latitude AND longitude=:longitude")
    fun getItemByPosition(latitude: Double, longitude: Double): PlaceEntity

    @Query("SELECT * FROM favorite_places")
    fun getAll(): List<PlaceEntity>
}