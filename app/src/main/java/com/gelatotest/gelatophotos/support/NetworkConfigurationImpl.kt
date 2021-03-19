package com.gelatotest.gelatophotos.support

import android.content.Context
import com.gelatotest.network.support.NetworkConfiguration
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

open class NetworkConfigurationImpl(private val context: Context) : NetworkConfiguration {

    override fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioScheduler(): Scheduler = Schedulers.io()

    override fun getHost() = "https://picsum.photos/"//"http://localhost:8081"

    override fun getCacheDir(): File = context.cacheDir

    override fun getCacheSize(): Long = 10 * 1024 * 1024

    override fun getTimeoutSeconds(): Long = 5
}
