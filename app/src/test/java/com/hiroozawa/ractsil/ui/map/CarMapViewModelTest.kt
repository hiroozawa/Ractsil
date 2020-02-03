package com.hiroozawa.ractsil.ui.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.ui.FakeCarRepository
import com.hiroozawa.ractsil.ui.model.CarMapUiState
import com.hiroozawa.ractsil.util.LiveDataTestUtil
import com.hiroozawa.ractsil.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class CarMapViewModelTest {
    // Subject under test
    private lateinit var viewModel: CarMapViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `load should trigger loading and retrieve data correctly`() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Trigger loading of cars
        val carRepository = FakeCarRepository()
        viewModel = CarMapViewModel(carRepository)
        viewModel.load()

        // Then progress indicator is shown
        assertTrue(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertFalse(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // And data correctly loaded
        ViewMatchers.assertThat(
            LiveDataTestUtil.getValue(viewModel.carMapState),
            instanceOf(CarMapUiState.MapUiData::class.java)
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load should trigger loading and trigger error`() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Trigger loading of cars
        val carRepository = FakeCarRepository(null)
        viewModel = CarMapViewModel(carRepository)
        viewModel.load()

        // Then progress indicator is shown
        assertTrue(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertFalse(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Error should be shown
        val errorMessageEvent = LiveDataTestUtil.getValue(viewModel.errorEvent)
        val errorMessageId = errorMessageEvent.getContentIfNotHandled()
        assertThat(errorMessageId, IsEqual(R.string.error))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load with selected car trigger loading and retrieve data correctly`() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        val selectedCarId = "0"
        // Trigger loading of carrs
        val carRepository = FakeCarRepository()
        viewModel = CarMapViewModel(carRepository)
        viewModel.load(selectedCarId)

        // Then progress indicator is shown
        assertTrue(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertFalse(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // And data correctly loaded
        ViewMatchers.assertThat(
            LiveDataTestUtil.getValue(viewModel.carMapState),
            instanceOf(CarMapUiState.MapUiData::class.java)
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load with selected car should trigger loading and trigger error`() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        val selectedCarId = "0"
        // Trigger loading of cars
        val carRepository = FakeCarRepository(null)
        viewModel = CarMapViewModel(carRepository)
        viewModel.load(selectedCarId)

        // Then progress indicator is shown
        assertTrue(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertFalse(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Error should be shown
        val errorMessageEvent = LiveDataTestUtil.getValue(viewModel.errorEvent)
        val errorMessageId = errorMessageEvent.getContentIfNotHandled()
        assertThat(errorMessageId, IsEqual(R.string.error))
    }
}