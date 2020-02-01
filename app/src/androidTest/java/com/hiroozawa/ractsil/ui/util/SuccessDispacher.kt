package com.hiroozawa.ractsil.ui.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.hiroozawa.ractsil.ui.util.AssetReaderUtil.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().context
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest) =
        if (request.path == "/codingtask/cars") {
            asset(context, "success.json").let {
                MockResponse().setResponseCode(200).setBody(it)
            }
        } else {
            MockResponse().setResponseCode(500)
        }
}

