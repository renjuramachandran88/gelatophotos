package com.gelatotest.gelatophotos

import android.content.Context
import com.gelatotest.data.DataLibrary
import com.gelatotest.domain.DomainLibrary
import com.gelatotest.gelatophotos.support.NetworkConfigurationImpl
import com.gelatotest.network.NetworkLibrary
import com.gelatotest.network.support.NetworkConfiguration
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class AppModule {
    @Provides
    fun providesNetworkConfiguration(context: Context): NetworkConfiguration =
        NetworkConfigurationImpl(context)

    @Provides
    fun providesNetworkLibrary(networkConfiguration: NetworkConfiguration) =
        NetworkLibrary(networkConfiguration)

    @Provides
    fun providesRetrofit(
        networkLibrary: NetworkLibrary
    ): Retrofit {
        val okHttpBuilder = networkLibrary.okHttpClient()
            .newBuilder()

        val okHttpClient = okHttpBuilder
            .build()

        return networkLibrary.retrofit().newBuilder().client(okHttpClient).build()
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun providesDomainLibrary(): DomainLibrary = DomainLibrary()

    @Provides
    fun providesDataLibrary(retrofit: Retrofit, networkConfiguration: NetworkConfiguration)
            : DataLibrary = DataLibrary(retrofit, networkConfiguration)
}