package com.hiroozawa.ractsil.ui.list

import androidx.annotation.StringRes

data class CarUiModel(
    val id: String,
    val ownerName: String,
    val imageUrl: String,
    val modelName: String,
    val makeName: String,
    val licensePlate: String,
    val fuelLevel: String,
    @StringRes
    val fuelType: Int,
    @StringRes
    val transmission: Int,
    @StringRes
    val innerCleanliness: Int
)