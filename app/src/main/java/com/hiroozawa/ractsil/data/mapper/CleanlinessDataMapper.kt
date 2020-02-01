package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.domain.InnerCleanliness

object CleanlinessDataMapper {
    operator fun invoke(cleanliness: String) = when (cleanliness) {
        "REGULAR" -> InnerCleanliness.REGULAR
        "CLEAN" -> InnerCleanliness.CLEAN
        "VERY_CLEAN" -> InnerCleanliness.VERY_CLEAN
        else -> InnerCleanliness.UNKNOWN
    }
}