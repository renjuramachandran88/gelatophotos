package com.gelatotest.gelatophotos

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.test.runner.AndroidJUnitRunner
import com.gelatotest.gelatophotos.support.NetworkConfigurationImpl
import com.gelatotest.network.support.NetworkConfiguration

class EspressoAppTestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        RxIdlerController.initialize()
        return super.newApplication(cl, MockApp::class.java.name, context)
    }
}