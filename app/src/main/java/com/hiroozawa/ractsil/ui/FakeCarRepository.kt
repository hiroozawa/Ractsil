package com.hiroozawa.ractsil.ui

import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import com.hiroozawa.ractsil.domain.Car

class FakeCarRepository : CarRepository {
    override suspend fun fetchCars(): Result<List<Car>> {
        return Result.Success(listOf(Car()))
    }

}
