package com.hiroozawa.ractsil.ui.navigation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.hiroozawa.ractsil.DaggerTestApplicationRule
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.ui.MainActivity
import com.hiroozawa.ractsil.ui.util.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @get:Rule
    val rule = DaggerTestApplicationRule()

    // An Idling Resource that waits for Data Binding to have no pending bindings
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    private val mockWebServer = MockWebServer()

    @Before
    fun before() {
        mockWebServer.start(8080)

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun navigate_clicksOnMapMenu() {
        // given
        mockWebServer.dispatcher = SuccessDispatcher()

        // when
        launchActivity()

        // then
        onView(withId(R.id.navigation_map)).perform(click())

        onView((withId(R.id.map)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigate_clicksOnMapMenuThenClicksBackToList() {
        // given
        mockWebServer.dispatcher = SuccessDispatcher()

        // when
        launchActivity()

        // then
        onView(withId(R.id.navigation_list)).perform(click())
        onView(withId(R.id.car_list)).perform(click())

        onView((withId(R.id.car_list)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigate_clicksOnMapMenuThenBackPress() {
        // given
        mockWebServer.dispatcher = SuccessDispatcher()

        // when
        launchActivity()

        // then
        onView(withId(R.id.navigation_map)).perform(click())
        pressBack()

        onView((withId(R.id.car_list)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigate_clicksOnItemMapButtonThenBackPresses() {
        // given
        mockWebServer.dispatcher = SuccessDispatcher()

        // when
        launchActivity()

        // then
        onView(withId(R.id.car_list))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ClickOnChildViewAction(R.id.navigate_map_button)
                    )
            )

        pressBack()

        onView((withId(R.id.car_list)))
            .check(matches(isDisplayed()))
    }

    private fun launchActivity(): ActivityScenario<MainActivity>? {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            // Disable animations in RecyclerView
            (activity.findViewById(R.id.car_list) as RecyclerView).itemAnimator = null
        }
        dataBindingIdlingResource.monitorActivity(activityScenario)
        return activityScenario
    }

}