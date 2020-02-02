package com.hiroozawa.ractsil.domain

data class CarFuel(
    val fuelType: FuelType = FuelType.UNKNOWN,
    val fuelLevel: Float = 0.0F
)

