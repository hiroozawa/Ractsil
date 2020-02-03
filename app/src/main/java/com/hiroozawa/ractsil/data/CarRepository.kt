package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.domain.Car

interface CarRepository {
    suspend fun fetchCars(forceUpdate: Boolean = false): Result<List<Car>>

    suspend fun getCar(carID: String): Result<Car>
}