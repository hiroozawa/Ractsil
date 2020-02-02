package com.hiroozawa.ractsil.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.ui.FakeCarRepository
import com.hiroozawa.ractsil.util.LiveDataTestUtil
import com.hiroozawa.ractsil.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CarListViewModelTest {

    // Subject under test
    private lateinit var viewModel: CarListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `init should trigger loading and retrieve data correctly`() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Trigger loading of cars
        val carRepository = FakeCarRepository()
        viewModel = CarListViewModel(carRepository)

        // Then progress indicator is shown
        assertTrue(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertFalse(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // And data correctly loaded
        assertThat(LiveDataTestUtil.getValue(viewModel.carsUiModel).size, IsEqual(4))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `init should trigger loading and trigger error`() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Trigger loading of cars
        val carRepository = FakeCarRepository(null)
        viewModel = CarListViewModel(carRepository)

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

        // Error occurred, list should be still empty
        assertThat(LiveDataTestUtil.getValue(viewModel.carsUiModel).size, IsEqual(0))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `refresh should trigger loading and retrieve data correctly`() {
        // Trigger loading of cars
        val carRepository = FakeCarRepository()
        viewModel = CarListViewModel(carRepository)

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Invokes refresh
        viewModel.refresh()

        // Then progress indicator is shown
        assertTrue(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertFalse(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // And data correctly loaded
        assertThat(LiveDataTestUtil.getValue(viewModel.carsUiModel).size, IsEqual(4))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `refresh should trigger loading and trigger error`() {
        // Trigger loading of cars
        val carRepository = FakeCarRepository(null)
        viewModel = CarListViewModel(carRepository)

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Invokes refresh
        viewModel.refresh()

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

        // Error occurred, list should be still empty
        assertThat(LiveDataTestUtil.getValue(viewModel.carsUiModel).size, IsEqual(0))
    }
}