package com.hiroozawa.ractsil.data

data class Car(
    val id: String,
    val name: String,
    val imageUrl: String,
    val make: String,
    val licencePlate: String,
    val fuelType: String,
    val fuelLevel: Float,
    val transmission: String,
    val innerCleanliness: String
)