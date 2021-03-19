package com.gelatotest.data.repository

import com.gelatotest.data.mapper.PhotoListDataMapper
import com.gelatotest.data.remote.PhotoListService
import com.gelatotest.domain.model.PicsumPhotos
import com.gelatotest.domain.repository.PhotoRepository
import io.reactivex.Observable
import javax.inject.Inject

class PhotoDataRepository @Inject constructor(
    private val mapper: PhotoListDataMapper,
    private val service: PhotoListService,
) : PhotoRepository {

    override fun getPhotoListRepo(pageNumber: Int): Observable<List<PicsumPhotos>> {
        return service.getPhotosListWithPageNumber(pageNumber)
            .map { mapper.mapFrom(it) }
            .onErrorReturn { emptyList() }
    }
}