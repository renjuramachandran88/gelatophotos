package com.gelatotest.gelatophotos

import android.content.Context
import com.gelatotest.gelatophotos.photolist.PhotoListActivity
import com.gelatotest.gelatophotos.photolist.PhotoListActivityTest
import com.gelatotest.gelatophotos.photolist.PhotoListModule
import com.gelatotest.gelatophotos.photoview.PhotoViewActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModuleForTest::class,
        ViewModelModule::class,
        PhotoListModule::class
    ]
)
interface AppComponentForTest {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponentForTest
    }

    fun inject(photoListActivityTest: PhotoListActivityTest)
}