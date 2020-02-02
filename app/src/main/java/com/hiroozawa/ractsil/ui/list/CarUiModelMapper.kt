package com.hiroozawa.ractsil.ui.list

import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.domain.FuelType
import com.hiroozawa.ractsil.domain.InnerCleanliness
import com.hiroozawa.ractsil.domain.Transmission
import java.text.NumberFormat

object CarUiModelMapper {
    operator fun invoke(carList: List<Car>) = carList.map { car ->
        CarUiModel(
            id = car.carId.id,
            ownerName = car.owner.name,
            imageUrl = car.carImage.url,
            modelName = car.model.modelName,
            makeName = car.make.name,
            licensePlate = car.licensePlate.code,
            fuelLevel = mapFuelLevel(car.fuel.fuelLevel),
            fuelType = mapFuelType(car.fuel.fuelType),
            transmission = mapTransmission(car.transmission),
            innerCleanliness = mapInnerCleanliness(car.innerCleanliness)
        )
    }

    private fun mapFuelLevel(fuelLevel: Float): String {
        val percentFormat = NumberFormat.getPercentInstance()
        return percentFormat.format(fuelLevel)
    }

    private fun mapFuelType(fuelType: FuelType): Int =
        when (fuelType) {
            FuelType.PETROL -> R.string.petrol
            FuelType.DIESEL -> R.string.diesel
            FuelType.UNKNOWN -> R.string.unknown
        }

    private fun mapTransmission(transmission: Transmission): Int =
        when (transmission) {
            Transmission.AUTO -> R.string.auto
            Transmission.MANUAL -> R.string.manual
            Transmission.UNKNOWN -> R.string.unknown
        }

    private fun mapInnerCleanliness(innerCleanliness: InnerCleanliness): Int =
        when (innerCleanliness) {
            InnerCleanliness.REGULAR -> R.string.regular
            InnerCleanliness.CLEAN -> R.string.clean
            InnerCleanliness.VERY_CLEAN -> R.string.very_clean
            InnerCleanliness.UNKNOWN -> R.string.unknown
        }
}