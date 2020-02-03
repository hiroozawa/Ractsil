package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.domain.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertNotEquals
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

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchCars should cache result at the consecutive non forceUpdate fetch`() =
        runBlockingTest {

            val cars = carRepository.fetchCars()
            val cars2 = carRepository.fetchCars()

            assertThat(cars, IsEqual(cars2))
        }


    @ExperimentalCoroutinesApi
    @Test
    fun `fetchCars should not cache result when forceUpdate fetch`() =
        runBlockingTest {

            val cars = carRepository.fetchCars()

            // Simulate a change in remote data source removing first item
            remoteDataSource.responseList?.removeIf { it.id == "0" }

            // Fetch again with forceUpdate
            val cars2 = carRepository.fetchCars(true)

            assertNotEquals(cars, cars2)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `getCar should return success when cache contains the car`() =
        runBlockingTest {
            val cars = carRepository.fetchCars()

            val firstCar = (cars as Result.Success).data.first()

            // Fetch again with forceUpdate
            val car = carRepository.getCar(firstCar.carId.id)

            assertThat(car, instanceOf(Result.Success::class.java))
            val carData = (car as Result.Success).data
            assertThat(carData, IsEqual(firstCar))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `getCar should return error when cache does not contain the requested car`() =
        runBlockingTest {
            val carID = "WRONG_ID"

            // Fetch again with forceUpdate
            val car = carRepository.getCar(carID)

            assertThat(car, instanceOf(Result.Error::class.java))
        }
}
