package dev.kisurin.favoriteplaces.repository.impl

import dev.kisurin.favoriteplaces.data.Place
import dev.kisurin.favoriteplaces.db.dao.PlaceDao
import dev.kisurin.favoriteplaces.repository.PlaceRepository
import dev.kisurin.favoriteplaces.repository.mapper.toEntity
import dev.kisurin.favoriteplaces.repository.mapper.toDto
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(private val placeDao: PlaceDao): PlaceRepository {
    override fun add(place: Place) = placeDao.add(place.toEntity())

    override fun update(place: Place) = placeDao.update(place.toEntity())

    override fun delete(place: Place) = placeDao.delete(place.toEntity())

    override fun deleteById(id: Long) = placeDao.deleteById(id)

    override fun getItemById(id: Long) = placeDao.getItemById(id).toDto()

    override fun getItemByPosition(latitude: Double, longitude: Double) =
        placeDao.getItemByPosition(latitude, longitude).toDto()

    override fun getAll() = placeDao.getAll().map { it.toDto() }
}