package dev.kisurin.favoriteplaces.ui.map

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.kisurin.favoriteplaces.R
import dev.kisurin.favoriteplaces.databinding.FragmentMapBinding

class MapFragment : Fragment(R.layout.fragment_map) {
    private val binding by viewBinding(FragmentMapBinding::bind)
    private val viewModel: MapViewModel by viewModels()
}