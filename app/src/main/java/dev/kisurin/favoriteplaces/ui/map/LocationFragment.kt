package dev.kisurin.favoriteplaces.ui.map

import android.Manifest
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import dev.kisurin.favoriteplaces.utils.TAG

@AndroidEntryPoint
abstract class LocationFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {
    var currentLocation: Location? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) fetchLocation()
        }

    private val mLocationCallback: LocationCallback by lazy {
        object: LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                updateLocation(locationResult.locations[0])
            }
        }
    }

    private val locationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).apply {
            setMaxUpdateAgeMillis(LocationRequest.Builder.IMPLICIT_MAX_UPDATE_AGE)
            setDurationMillis(Long.MAX_VALUE)
            setMaxUpdateDelayMillis(10000L)
            setMinUpdateIntervalMillis(LocationRequest.Builder.IMPLICIT_MIN_UPDATE_INTERVAL)
            setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchLocation()
    }

    override fun onResume() {
        super.onResume()
        isGpsEnabled()
    }

    open fun updateLocation(location: Location?) {
        currentLocation = location
    }

    private fun fetchLocation() {
        val isLocationPermission = ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (isLocationPermission) {
            val task = fusedLocationClient.lastLocation
            task.addOnSuccessListener { location ->
                location?.let { updateLocation(it) }
            }.addOnFailureListener {
                Log.d(TAG, it.stackTraceToString())
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, null)
        } else requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun isGpsEnabled() {
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(requireActivity(), 100)
                } catch (_: IntentSender.SendIntentException) {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            fusedLocationClient.removeLocationUpdates(mLocationCallback)
        } catch (_: IllegalArgumentException) {}
    }
}