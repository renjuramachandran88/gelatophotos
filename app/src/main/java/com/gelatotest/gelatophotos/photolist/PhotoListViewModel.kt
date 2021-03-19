package com.gelatotest.gelatophotos.photolist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gelatotest.domain.usecase.PhotoUseCase
import com.gelatotest.gelatophotos.*
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class PhotoListViewModel @Inject constructor(
    useCase: PhotoUseCase,
    mapper: PhotoModelMapper,
    private val compositeDisposable: CompositeDisposable
) :ViewModel(), LifecycleObserver {

    var photoList: LiveData<PagedList<PhotoModel>>

    private val photoDataSourceFactory: PhotoDataSourceFactory =
        PhotoDataSourceFactory(useCase, mapper, compositeDisposable)

    init {
        Executors.newFixedThreadPool(10)
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10 * 2)
            .setEnablePlaceholders(false)
            .build()
        photoList = LivePagedListBuilder(photoDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap(photoDataSourceFactory.photoDataSourceLiveData, PhotoDataSource::state)

    fun retry() {
        photoDataSourceFactory.photoDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return photoList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}