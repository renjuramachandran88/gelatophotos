package com.gelatotest.gelatophotos

import android.content.Context
import com.gelatotest.network.support.NetworkConfiguration
import io.reactivex.Scheduler
import java.io.File

class EspressoNetworkConfigurationImpl(context: Context) :
    NetworkConfiguration {

    override fun getHost(): String {
        return "http://localhost:8081"
    }

    override fun getCacheDir(): File {
        TODO("Not yet implemented")
    }

    override fun getCacheSize(): Long {
        TODO("Not yet implemented")
    }

    override fun getTimeoutSeconds(): Long {
        TODO("Not yet implemented")
    }

    override fun mainThreadScheduler(): Scheduler {
        TODO("Not yet implemented")
    }

    override fun ioScheduler(): Scheduler {
        TODO("Not yet implemented")
    }


}