package com.hiroozawa.ractsil.ui.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ErrorDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest) =
        MockResponse().setResponseCode(404)
}

