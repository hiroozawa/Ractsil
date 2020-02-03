package com.hiroozawa.ractsil.ui.model.mappers

import com.google.android.gms.maps.model.LatLngBounds
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.model.toLatLng

object LatLngBoundsMapper {

    operator fun invoke(cars: List<Car>): LatLngBounds {
        val boundsBuilder = LatLngBounds.Builder()

        cars.forEach { car ->
            boundsBuilder.include(car.coordinate.toLatLng())
        }

        return boundsBuilder.build()
    }
}
