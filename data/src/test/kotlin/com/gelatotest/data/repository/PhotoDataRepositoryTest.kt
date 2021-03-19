package com.gelatotest.data.repository

import com.gelatotest.data.entity.PicsumPhotosEntity
import com.gelatotest.data.mapper.PhotoListDataMapper
import com.gelatotest.data.remote.PhotoListService
import com.gelatotest.domain.model.PicsumPhotos
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class PhotoDataRepositoryTest @Inject constructor() {

    @InjectMocks
    private lateinit var subject: PhotoDataRepository

    @Mock
    private lateinit var service: PhotoListService

    @Mock
    private lateinit var mapper: PhotoListDataMapper

    @Mock
    private lateinit var entity: PicsumPhotosEntity

    @Mock
    private lateinit var picsumPhotos: PicsumPhotos

    @Test
    fun getPhotoListRepo_givenPageNumber_callsPicsumPhotosApi() {
        `when`(service.getPhotosListWithPageNumber(1))
            .thenReturn(Observable.just(listOf(entity)))
        `when`(mapper.mapFrom(listOf(entity))).thenReturn(listOf(picsumPhotos))

        subject.getPhotoListRepo(1).test()

        verify(service).getPhotosListWithPageNumber(1)
    }

    @Test
    fun getPhotoListRepo_givenPageNumber_callsPicsumPhotosApiSuccessResponse() {
        `when`(service.getPhotosListWithPageNumber(1))
            .thenReturn(Observable.just(listOf(entity)))
        `when`(mapper.mapFrom(listOf(entity))).thenReturn(listOf(picsumPhotos))

        val actual = subject.getPhotoListRepo(1).test()

        actual.assertValue(listOf(picsumPhotos))
    }

    @Test
    fun getPhotoListRepo_givenPageNumber_callsErrorResponse() {
        `when`(service.getPhotosListWithPageNumber(1))
            .thenReturn(Observable.error(Throwable("error")))

        val actual = subject.getPhotoListRepo(1).test()

        actual.assertValue(emptyList())
    }
}