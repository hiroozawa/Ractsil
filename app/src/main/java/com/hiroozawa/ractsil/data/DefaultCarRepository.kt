package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.data.mapper.CarDataMapper
import com.hiroozawa.ractsil.data.remote.CarRemoteDataSource
import com.hiroozawa.ractsil.domain.Car
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
                .let {
                    Result.Success(CarDataMapper(it))
                }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}