package com.hiroozawa.ractsil.ui.model.mappers

import com.google.android.gms.maps.model.LatLngBounds
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.model.toLatLng

object SingleLatLngBoundsMapper {

    operator fun invoke(
        cars: List<Car>,
        selectedCarId: String
    ): LatLngBounds = cars
        .first { it.carId.id == selectedCarId }
        .let {
            LatLngBounds.Builder().include(it.coordinate.toLatLng()).build()
        }
}

