package com.gelatotest.gelatophotos

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gelatotest.domain.usecase.PhotoUseCase
import io.reactivex.disposables.CompositeDisposable

class PhotoDataSourceFactory(
    private val useCase: PhotoUseCase,
    private val mapper: PhotoModelMapper,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, PhotoModel>() {
     val photoDataSourceLiveData = MutableLiveData<PhotoDataSource>()

    override fun create(): DataSource<Int, PhotoModel> {
        val photoDataSource = PhotoDataSource(useCase, mapper, compositeDisposable)
        photoDataSourceLiveData.postValue(photoDataSource)
        return photoDataSource
    }
}