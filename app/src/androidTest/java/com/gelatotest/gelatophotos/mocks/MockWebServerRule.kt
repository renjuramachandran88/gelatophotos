package com.gelatotest.gelatophotos.mocks

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import java.io.IOException

class MockWebServerRule : ExternalResource {

    val mockWebServer = MockWebServer()
    val dispatcher: Dispatcher

    constructor() : this(MockResponseDispatcher())

    constructor(dispatcher: Dispatcher) {
        this.dispatcher = dispatcher
    }

    @Throws(IOException::class)
    override fun before() {
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start(8081)
    }

    override fun after() {
        try {
            mockWebServer.shutdown()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

