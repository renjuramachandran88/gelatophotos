package com.gelatotest.gelatophotos.photolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.gelatotest.gelatophotos.PhotoModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoListViewModelTest {
    @InjectMocks
    private lateinit var subject: PhotoListViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var photoModel: PhotoModel

    @Before
    fun setUp() {
        subject.photoList = MutableLiveData()
    }


}