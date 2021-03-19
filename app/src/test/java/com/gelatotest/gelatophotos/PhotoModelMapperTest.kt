package com.gelatotest.gelatophotos

import com.gelatotest.domain.model.PicsumPhotos
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoModelMapperTest {

    @InjectMocks
    private lateinit var subject: PhotoModelMapper

    @Test
    fun mapTo_givenListOfPicsumPhoto_returnsListOfPhotoModel() {
        val actual = subject.mapTo(getPicsumPhoto())

        assertThat(actual.size).isEqualTo(2)

        assertThat(actual[0])
            .extracting("photoId", "author", "width", "height", "url", "downloadUrl")
            .containsExactly("id1", "author1", 111, 111, "url1", "downloadurl1")

        assertThat(actual[1])
            .extracting("photoId", "author", "width", "height", "url", "downloadUrl")
            .containsExactly("id2", "author2", 222, 222, "url2", "downloadurl2")
    }

    private fun getPicsumPhoto(): List<PicsumPhotos> {
        return listOf(
            PicsumPhotos(
                "id1",
                "author1",
                111,
                111,
                "url1",
                "downloadurl1"
            ),
            PicsumPhotos(
                "id2",
                "author2",
                222,
                222,
                "url2",
                "downloadurl2"
            )
        )
    }


}