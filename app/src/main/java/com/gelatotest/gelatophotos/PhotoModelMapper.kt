package com.gelatotest.gelatophotos

import com.gelatotest.domain.model.PicsumPhotos
import javax.inject.Inject

class PhotoModelMapper @Inject constructor() {
    fun mapTo(picsumPhotos: List<PicsumPhotos>): List<PhotoModel> {
        return picsumPhotos.map {
            PhotoModel(it.photoId, it.author, it.width, it.height, it.url, it.downloadUrl)
        }
    }
}