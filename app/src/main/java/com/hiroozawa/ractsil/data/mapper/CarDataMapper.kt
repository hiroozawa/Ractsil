package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.data.remote.CarResponse
import com.hiroozawa.ractsil.domain.*

object CarDataMapper {
    operator fun invoke(carResponseList: List<CarResponse>): List<Car> =
        carResponseList.map { carResp ->
            Car(
                carId = CarId(id = carResp.id),
                owner = Owner(name = carResp.name),
                coordinate = Coordinate(carResp.latitude, carResp.longitude),
                model = Model(carResp.modelIdentifier, carResp.modelName),
                make = Make(carResp.make),
                color = Color(carResp.color),
                fuel = CarFuel(
                    fuelLevel = carResp.fuelLevel,
                    fuelType = FuelTypeDataMapper(carResp.fuelType)
                ),
                transmission = TransmissionDataMapper(carResp.transmission),
                licensePlate = LicencePlate(carResp.licensePlate),
                innerCleanliness = CleanlinessDataMapper(carResp.innerCleanliness),
                carImage = CarImage(carResp.carImageUrl)
            )
        }
}