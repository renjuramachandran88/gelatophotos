package com.gelatotest.gelatophotos.mocks

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class PhotoListRequestHandler : RecordedRequestHandler() {

    override fun canHandleRequest(request: RecordedRequest): Boolean {
        return request.method == "GET" && request.path.contains(PHOTO_ENDPOINT)
    }

    override fun getResponse(request: RecordedRequest): MockResponse {
        val body = readJsonFile("photos/get.json")
        return getResponseWithBody(200, body)
    }

    companion object {
        private const val PHOTO_ENDPOINT = "/v2/list"
    }
}
