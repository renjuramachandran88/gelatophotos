package com.gelatotest.gelatophotos

class MockApp: GelatoPhotosApplication() {
    val appComponentForTest: AppComponentForTest by lazy {
        DaggerAppComponentForTest.factory().create(applicationContext)
    }


}
