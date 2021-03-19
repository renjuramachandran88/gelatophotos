package com.gelatotest.gelatophotos.photoview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.gelatotest.gelatophotos.GelatoPhotosApplication
import com.gelatotest.gelatophotos.PhotoModel
import com.gelatotest.gelatophotos.R
import kotlinx.android.synthetic.main.activity_photo_view.*

class PhotoViewActivity : AppCompatActivity() {
    private lateinit var viewDataBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as GelatoPhotosApplication).appComponent
            .inject(this)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_view)
        Glide.with(this)
            .load(getPhotoModel().downloadUrl)
            .into(full_image)
    }

    private fun getPhotoModel(): PhotoModel =
        intent.getParcelableExtra(IntentExtras.PHOTO_MODEL_ITEM)!!

    object IntentExtras {
        const val PHOTO_MODEL_ITEM = "photoModelItem"
    }

    companion object {
        fun startInstance(
            context: Context,
            photoModel: PhotoModel
        ) {
            val intent = Intent(context, PhotoViewActivity::class.java)
            intent.putExtra(IntentExtras.PHOTO_MODEL_ITEM, photoModel)
            context.startActivity(intent)
        }
    }
}