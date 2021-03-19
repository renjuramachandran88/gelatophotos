package com.gelatotest.gelatophotos

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gelatotest.domain.usecase.PhotoUseCase
import com.gelatotest.gelatophotos.State.*
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers.io

class PhotoDataSource(
    private val useCase: PhotoUseCase,
    private val mapper: PhotoModelMapper,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, PhotoModel>() {
    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PhotoModel>
    ) {
        updateState(LOADING)
        val disposable = useCase.getPhotoList(1)
            .subscribe({
                updateState(DONE)
                callback.onResult(mapper.mapTo(it), null, 2)
                mapper.mapTo(it)
            }, {
                updateState(ERROR)
                setRetry { loadInitial(params, callback) }
            })
        compositeDisposable.add(disposable)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PhotoModel>
    ) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoModel>) {
        updateState(LOADING)
        val disposable = useCase.getPhotoList(params.key )
            .subscribe({
                updateState(DONE)
                callback.onResult(mapper.mapTo(it), params.key+1)
            }, {
                updateState(ERROR)
                setRetry { loadAfter(params, callback) }
            })
        compositeDisposable.add(disposable)
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(io())
                    .observeOn(mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}