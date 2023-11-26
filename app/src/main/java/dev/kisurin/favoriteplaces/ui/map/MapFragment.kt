package dev.kisurin.favoriteplaces.ui.map

import android.content.Context
import android.graphics.Rect
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.kisurin.favoriteplaces.R
import dev.kisurin.favoriteplaces.databinding.FragmentMapBinding
import dev.kisurin.favoriteplaces.utils.MY_LOCATION_ZOOM
import dev.kisurin.favoriteplaces.utils.UKRAINE
import dev.kisurin.favoriteplaces.utils.geoPoint
import dev.kisurin.favoriteplaces.utils.zoomTo
import dev.kisurin.favoriteplaces.utils.setDirectionIcon
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@AndroidEntryPoint
class MapFragment : LocationFragment(R.layout.fragment_map) {
    private val binding by viewBinding(FragmentMapBinding::bind)
    private val viewModel: MapViewModel by viewModels()

    private var firstRun = true

    private val locationProvider: GpsMyLocationProvider by lazy {
        GpsMyLocationProvider(requireContext())
    }
    private val myLocation: MyLocationNewOverlay by lazy {
        MyLocationNewOverlay(binding.map).apply {
            enableMyLocation()
            setDirectionIcon(resources, R.drawable.round_navigation_blue_48)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().loadConfiguration()
        binding.map.configure()
        binding.myLocation.setOnClickListener {
            moveTo(myLocation.myLocation)
        }
    }

    override fun updateLocation(location: Location?) {
        super.updateLocation(location)
        location?.run {
            myLocation.onLocationChanged(this, locationProvider)
            if (firstRun) {
                moveTo(geoPoint)
                firstRun = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireContext().loadConfiguration()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        requireContext().saveConfiguration()
        binding.map.onPause()
    }

    private fun moveTo(location: GeoPoint) =
        binding.map.zoomTo(location, MY_LOCATION_ZOOM)

    private fun MapView.configure() = apply {
        setTileSource(TileSourceFactory.MAPNIK)
        binding.map.zoomTo(UKRAINE)
        setMultiTouchControls(true)
        getLocalVisibleRect(Rect())
        overlays.add(myLocation)
    }
}

private fun Context.loadConfiguration() = Configuration
    .getInstance()
    .load(this, PreferenceManager.getDefaultSharedPreferences(this))

private fun Context.saveConfiguration() = Configuration
    .getInstance()
    .save(this, PreferenceManager.getDefaultSharedPreferences(this))