package com.hiroozawa.ractsil.ui

import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.domain.CarId
import com.hiroozawa.ractsil.domain.Owner

/**
 * Class that generates fake data for tests
 * @param carList if not null [Result.Success] with data will be returned,
 * if null returns [Result.Error]
 * */
class FakeCarRepository(
    private val carList: List<Car>? = createFakeCarData()
) : CarRepository {
    override suspend fun fetchCars(): Result<List<Car>> {
        carList?.let {
            return Result.Success(carList)
        }

        return Result.Error(Exception("Test repository exception"))
    }
}

private fun createFakeCarData(): List<Car> =
    listOf(0, 1, 2, 3)
        .map {
            Car(
                carId = CarId(it.toString()),
                owner = Owner("User $it")
            )
        }