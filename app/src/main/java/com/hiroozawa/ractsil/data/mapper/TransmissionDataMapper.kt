package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.domain.Transmission

object TransmissionDataMapper {
    operator fun invoke(transmission: String) = when (transmission) {
        "A" -> Transmission.AUTO
        "M" -> Transmission.MANUAL
        else -> Transmission.UNKNOWN
    }
}