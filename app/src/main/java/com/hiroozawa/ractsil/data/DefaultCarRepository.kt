package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.data.mapper.CarDataMapper
import com.hiroozawa.ractsil.data.remote.CarRemoteDataSource
import com.hiroozawa.ractsil.domain.Car
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implements a simple repository pattern that either fetches from remote api or from cache.
 * */
@Singleton
class DefaultCarRepository @Inject constructor(
    private val remoteDataSource: CarRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CarRepository {
    private var cache = Result.Success(emptyList<Car>())

    /**
     *  Fetches a list of cars
     *  @param forceUpdate when true, it will always fetch from remote, when false it only fetches
     *  when cache is empty.
     * */
    override suspend fun fetchCars(forceUpdate: Boolean): Result<List<Car>> =
        withContext(ioDispatcher) {
            try {
                fetchFromCacheOrRemote(forceUpdate)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private suspend fun fetchFromCacheOrRemote(forceUpdate: Boolean): Result.Success<List<Car>> =
        if (cache.data.isEmpty() || forceUpdate) {
            fetchAndUpdateCache()
        } else {
            cache
        }

    private suspend fun fetchAndUpdateCache(): Result.Success<List<Car>> =
        remoteDataSource.fetchCars()
            .let {
                Result.Success(CarDataMapper(it))
            }
            .also {
                cache = it
            }

    /**
     *  Returns a car given an CarId, for now it only fetches from cache. For simplicity reasons we
     *  didn't implement a concurrent map.
     *  @param carID is the car if to look for in cache
     * */
    override suspend fun getCar(carID: String): Result<Car> = withContext(ioDispatcher) {
        return@withContext cache.data
            .firstOrNull { car ->
                car.carId.id == carID
            }.let { car ->
                if (car == null) {
                    Result.Error(Exception("Car not found in cache"))
                } else {
                    Result.Success(car)
                }
            }
    }


}