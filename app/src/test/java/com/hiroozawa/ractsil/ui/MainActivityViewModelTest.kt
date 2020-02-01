package com.hiroozawa.ractsil.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.hiroozawa.ractsil.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityViewModelTest {

    // Subject under test
    private lateinit var viewModel: MainActivityViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        val carRepository = FakeCarRepository()
        viewModel = MainActivityViewModel(carRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load should trigger loading and retrieve data correctly`() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Trigger loading of tasks
        viewModel.load()

        // Then progress indicator is shown
        assertTrue(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertFalse(LiveDataTestUtil.getValue(viewModel.dataLoading))

        // And data correctly loaded
        assertThat(LiveDataTestUtil.getValue(viewModel.cars).size, IsEqual(1))
    }
}