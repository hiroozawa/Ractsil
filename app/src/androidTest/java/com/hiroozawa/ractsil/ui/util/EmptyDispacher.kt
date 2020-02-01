package com.hiroozawa.ractsil.ui.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class EmptyDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().context
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest) =
        MockResponse().setResponseCode(200).setBody("[]")
}

