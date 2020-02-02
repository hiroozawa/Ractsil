package com.hiroozawa.ractsil.domain

data class Car(
    val carId: CarId = CarId(UNKNOWN),
    val owner: Owner = Owner(UNKNOWN),
    val coordinate: Coordinate = Coordinate(),
    val model: Model = Model(),
    val make: Make = Make(UNKNOWN),
    val color: Color = Color(UNKNOWN),
    val fuel: CarFuel = CarFuel(),
    val transmission: Transmission = Transmission.UNKNOWN,
    val licensePlate: LicencePlate = LicencePlate(UNKNOWN),
    val innerCleanliness: InnerCleanliness = InnerCleanliness.UNKNOWN,
    val carImage: CarImage = CarImage(UNKNOWN)
)

const val UNKNOWN = "UNKNOWN"