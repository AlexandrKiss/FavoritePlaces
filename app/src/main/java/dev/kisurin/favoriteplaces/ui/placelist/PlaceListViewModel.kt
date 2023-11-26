package dev.kisurin.favoriteplaces.ui.placelist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kisurin.favoriteplaces.repository.PlaceRepository
import javax.inject.Inject

@HiltViewModel
class PlaceListViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
) : ViewModel() {
}