package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.domain.Car

interface CarRepository {
    suspend fun fetchCars(): Result<List<Car>>
}