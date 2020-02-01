package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.domain.Transmission

object TransmissionDataMapper {
    operator fun invoke(transmission: String) = when (transmission) {
        "A" -> Transmission.AUTOMATIC
        "M" -> Transmission.MANUAL
        else -> Transmission.UNKNOWN
    }
}