package com.hiroozawa.ractsil.data


interface CarRepository {
    suspend fun fetchCars(): Result<List<Car>>
}