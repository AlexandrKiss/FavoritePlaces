package dev.kisurin.favoriteplaces.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.kisurin.favoriteplaces.db.PlaceDataBase
import dev.kisurin.favoriteplaces.repository.PlaceRepository
import dev.kisurin.favoriteplaces.repository.impl.PlaceRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun providePlaceDataBase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext, PlaceDataBase::class.java, "Favorite_Places"
    ).build()

    @Provides
    fun providePlaceDao(placeDataBase: PlaceDataBase) = placeDataBase.placeDao()

    @Provides
    fun providePlaceRepository(placeRepository: PlaceRepositoryImpl): PlaceRepository =
        placeRepository
}