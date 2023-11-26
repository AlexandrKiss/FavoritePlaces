package dev.kisurin.favoriteplaces.utils

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.location.Location
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

val UKRAINE = GeoPoint(48.5858825, 31.1994266)
const val COUNTRY_ZOOM = 6.25
const val MY_LOCATION_ZOOM = 17.25

fun MapView.zoomTo(point: GeoPoint, levelZoom: Double = COUNTRY_ZOOM, speed: Long = 1L) =
    controller.animateTo(point, levelZoom, speed)

fun MapView.zoomTo(point: Location, levelZoom: Double = COUNTRY_ZOOM, speed: Long = 1L) =
    controller.animateTo(point.geoPoint, levelZoom, speed)

val Location.geoPoint: GeoPoint
    get() = GeoPoint(latitude, longitude)

fun MyLocationNewOverlay.setDirectionIcon(resources: Resources, resource: Int) =
    setDirectionIcon(BitmapFactory.decodeResource(resources, resource))