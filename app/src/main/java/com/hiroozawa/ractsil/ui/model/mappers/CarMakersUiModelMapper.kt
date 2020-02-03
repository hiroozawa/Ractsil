package com.hiroozawa.ractsil.ui.model.mappers

import com.google.android.gms.maps.model.MarkerOptions
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.model.CarMarkerUiModel
import com.hiroozawa.ractsil.ui.model.toLatLng

object CarMakersUiModelMapper {
    operator fun invoke(carList: List<Car>): List<CarMarkerUiModel> =
        carList.map { car ->
            CarMarkerUiModel(
                carId = car.carId.id,
                markerOptions = MarkerOptions().position(car.coordinate.toLatLng())
            )
        }
}