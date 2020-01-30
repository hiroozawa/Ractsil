package com.hiroozawa.ractsil.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class DefaultCarRepositoryTest {

    private val remoteDataSource = FakeCarRemoteDataSource()

    // Class under test
    private lateinit var carRepository: DefaultCarRepository

    @Before
    fun setUp() {
        carRepository = DefaultCarRepository(
            remoteDataSource = remoteDataSource,
            ioDispatcher = Dispatchers.Unconfined
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchCars should return list of cars when remoteDataSource result is successful`() =
        runBlockingTest {
            val cars = carRepository.fetchCars()

            assertThat(cars, instanceOf(Result.Success::class.java))

            val carData: List<Car> = (cars as Result.Success<List<Car>>).data
            assertThat(carData.size, IsEqual(4))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchCars should return error when repository throws exception`() =
        runBlockingTest {

            val errorRemoteDataSource = FakeCarRemoteDataSource(null)
            carRepository = DefaultCarRepository(
                remoteDataSource = errorRemoteDataSource,
                ioDispatcher = Dispatchers.Unconfined
            )

            val cars = carRepository.fetchCars()

            assertThat(cars, instanceOf(Result.Error::class.java))
        }
}
