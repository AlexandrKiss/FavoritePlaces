package dev.kisurin.favoriteplaces.ui.placelist

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.kisurin.favoriteplaces.R
import dev.kisurin.favoriteplaces.databinding.FragmentPlaceListBinding

class PlaceListFragment : Fragment(R.layout.fragment_place_list) {
    private val binding by viewBinding(FragmentPlaceListBinding::bind)
    private val viewModel: PlaceListViewModel by viewModels()
}