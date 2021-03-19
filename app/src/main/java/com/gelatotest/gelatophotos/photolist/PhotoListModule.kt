package com.gelatotest.gelatophotos.photolist

import androidx.lifecycle.ViewModel
import com.gelatotest.gelatophotos.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PhotoListModule {
    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(PhotoListViewModel::class)
    abstract fun bindsPhotoListViewModel(photoListViewModel: PhotoListViewModel) : ViewModel

}