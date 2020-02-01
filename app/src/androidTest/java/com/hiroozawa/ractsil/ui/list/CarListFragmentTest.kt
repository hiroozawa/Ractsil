package com.hiroozawa.ractsil.ui.list

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.ui.MainActivity
import com.hiroozawa.ractsil.ui.util.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Error
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class CarListFragmentTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

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
    fun load_whenRepositoryReturnsData() {
        // when
        mockWebServer.dispatcher = SuccessDispatcher()
        
        launchActivity()

        // then
        onView(withId(R.id.car_list))
            .check(matches(isDisplayed()))
        onView(withText("Vanessa"))
            .check(matches(isDisplayed()))
        onView(withText("Regine"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun load_whenRepositoryIsEmpty() {
        // when
        mockWebServer.dispatcher = EmptyDispatcher()

        launchActivity()

        // then
        onView(withText(R.string.no_cars))
            .check(matches(isDisplayed()))
    }

    @Test
    fun load_whenRepositoryReturnsError() {
        // when
        mockWebServer.dispatcher = ErrorDispatcher()

        launchActivity()

        // then
        onView(withId(R.id.emptyLayout))
            .check(matches(isDisplayed()))
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.error)))
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