package com.hiroozawa.ractsil.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hiroozawa.ractsil.ui.FakeCarRepository
import com.hiroozawa.ractsil.util.LiveDataTestUtil
import com.hiroozawa.ractsil.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class CarDetailViewModelTest {
    // Subject under test
    private lateinit var viewModel: CarDetailViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `load should return same car when cache has data`() {
        val carID = "0"

        // Trigger loading of a car
        val carRepository = FakeCarRepository()
        viewModel = CarDetailViewModel(carRepository)
        viewModel.load(carID)

        // And data correctly loaded
        val resultCarId = LiveDataTestUtil.getValue(viewModel.car).id
        assertThat(resultCarId, IsEqual(carID))
    }

    @Test
    fun `load should return error when cache doest not contain car`() {
        val carID = "invalidCarId"

        // Trigger loading of a car
        val carRepository = FakeCarRepository()
        viewModel = CarDetailViewModel(carRepository)
        viewModel.load(carID)

        // Assert error livedata is true
        assertTrue(LiveDataTestUtil.getValue(viewModel.error))
    }
}