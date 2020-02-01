package com.hiroozawa.ractsil.domain

data class CarFuel(
    val fuelTypeType: FuelType = FuelType.UNKNOWN,
    val fuelLevel: Float = 0.0F
)

