package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.data.remote.CarResponse
import com.hiroozawa.ractsil.domain.*

object CarDataMapper {
    operator fun invoke(carResponseList: List<CarResponse>): List<Car> =
        carResponseList.map { carResp ->
            Car(
                carId = CarId(id = carResp.id),
                owner = Owner(name = carResp.name),
                coordinate = mapCoordinate(carResp),
                model = mapModel(carResp),
                make = Make(carResp.make),
                color = Color(carResp.color),
                fuel = mapCarFuel(carResp),
                transmission = TransmissionDataMapper(carResp.transmission),
                licensePlate = LicencePlate(carResp.licensePlate),
                innerCleanliness = CleanlinessDataMapper(carResp.innerCleanliness),
                carImage = CarImage(carResp.carImageUrl)
            )
        }

    private fun mapCoordinate(carResp: CarResponse) =
        Coordinate(carResp.latitude, carResp.longitude)

    private fun mapModel(carResp: CarResponse) =
        Model(carResp.modelIdentifier, carResp.modelName)

    private fun mapCarFuel(carResp: CarResponse) =
        CarFuel(FuelTypeDataMapper(carResp.fuelType), carResp.fuelLevel)
}