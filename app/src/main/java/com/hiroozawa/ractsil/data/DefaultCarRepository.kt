package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.data.remote.CarRemoteDataSource
import com.hiroozawa.ractsil.data.remote.CarResponse
import kotlinx.android.synthetic.main.car_item.view.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DefaultCarRepository @Inject constructor(
    private val remoteDataSource: CarRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CarRepository {

    override suspend fun fetchCars(): Result<List<Car>> = withContext(ioDispatcher) {
        return@withContext try {
            remoteDataSource.fetchCars()
                .map(carDataMapper())
                .toList()
                .let { Result.Success(it) }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun carDataMapper(): (CarResponse) -> Car =
        { Car(
            id = it.id,
            name = it.name,
            imageUrl = it.carImageUrl,
            make = it.make,
            licencePlate = it.licensePlate,
            fuelType =  it.fuelType,
            fuelLevel = it.fuelLevel,
            innerCleanliness = it.innerCleanliness,
            transmission = it.transmission
        ) }

}