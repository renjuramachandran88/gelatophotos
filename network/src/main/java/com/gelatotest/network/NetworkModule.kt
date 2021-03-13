package com.gelatotest.network

import com.gelatotest.network.support.NetworkConfiguration
import com.gelatotest.network.support.RxThreadCallAdapter
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpCache(configuration: NetworkConfiguration): Cache {
        return Cache(configuration.getCacheDir(), configuration.getCacheSize())
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        configuration: NetworkConfiguration
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            readTimeout(configuration.getTimeoutSeconds(), TimeUnit.SECONDS)
            connectTimeout(configuration.getTimeoutSeconds(), TimeUnit.SECONDS)
            cache(cache)
        }.build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        configuration: NetworkConfiguration
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(configuration.getHost())
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(
                RxThreadCallAdapter(
                    configuration.ioScheduler(),
                    configuration.mainThreadScheduler()
                )
            )
            client(okHttpClient)
        }.build()
    }
}
