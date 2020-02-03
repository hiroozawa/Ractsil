package com.hiroozawa.ractsil.ui.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.hiroozawa.ractsil.domain.Coordinate

sealed class CarMapUiState {
    data class MapUiData(
        val latLngBounds: LatLngBounds,
        val carMarkers: List<CarMarkerUiModel>
    ) : CarMapUiState()

    object Empty : CarMapUiState()
}


fun Coordinate.toLatLng() = LatLng(latitude, longitude)