package com.gelatotest.gelatophotos.mocks

import android.os.Handler
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockResponseDispatcher internal constructor() : Dispatcher() {

    private val photoListRequestHandler = PhotoListRequestHandler()

    override fun dispatch(request: RecordedRequest): MockResponse {

        return if (photoListRequestHandler.canHandleRequest(request)) {
            photoListRequestHandler.getResponse(request)
        } else {
            throwUnsupportedException("Could not handle", request.path)
        }
    }

    private fun throwUnsupportedException(message: String, path: String): MockResponse {
        val mainThreadHandler = Handler(
            InstrumentationRegistry.getInstrumentation().targetContext.mainLooper
        )
        mainThreadHandler.post { throw UnsupportedOperationException("$message $path") }

        throw UnsupportedOperationException()
    }
}
