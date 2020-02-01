package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.domain.FuelType

object FuelTypeDataMapper {
    operator fun invoke(fuelType: String) = when (fuelType) {
        "D" -> FuelType.DIESEL
        "P" -> FuelType.PETROLEUM
        else -> FuelType.UNKNOWN
    }
}