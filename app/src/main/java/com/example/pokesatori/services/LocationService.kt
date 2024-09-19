package com.example.pokesatori.services

import android.annotation.SuppressLint
import androidx.activity.result.launch
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority


class LocationService(private val fusedLocationClient: FusedLocationProviderClient) {

	@SuppressLint("MissingPermission")
	fun startLocationUpdates(): Flow<android.location.Location> {
		val locationRequest = LocationRequest.Builder(
			Priority.PRIORITY_HIGH_ACCURACY,
			1000L
		).build()
		return callbackFlow {
			val locationCallback = object : LocationCallback() {
				override fun onLocationResult(locationResult: LocationResult) {
					locationResult.locations.forEach { location ->
						trySend(location)
					}
				}
				override fun onLocationAvailability(availability: LocationAvailability) {
					if (!availability.isLocationAvailable) {
						close()
					}
				}
			}
			fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
			awaitClose {
				fusedLocationClient.removeLocationUpdates(locationCallback)
			}
		}
	}

}
