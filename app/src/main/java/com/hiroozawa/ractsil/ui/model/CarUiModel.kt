package com.hiroozawa.ractsil.ui.model

import androidx.annotation.StringRes
import com.hiroozawa.ractsil.R

data class CarUiModel(
    val id: String = "",
    val ownerName: String = "",
    val imageUrl: String = "",
    val modelName: String = "",
    val makeName: String = "",
    val licensePlate: String = "",
    val fuelLevel: String = "",
    @StringRes
    val fuelType: Int = R.string.empty,
    @StringRes
    val transmission: Int = R.string.empty,
    @StringRes
    val innerCleanliness: Int = R.string.empty
)