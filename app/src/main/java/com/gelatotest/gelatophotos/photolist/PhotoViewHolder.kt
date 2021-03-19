package com.gelatotest.gelatophotos.photolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gelatotest.gelatophotos.PhotoModel
import com.gelatotest.gelatophotos.R
import kotlinx.android.synthetic.main.layout_photo_item.view.*

class PhotoViewHolder(view: View,
private val onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(photoModel: PhotoModel?) {
        if (photoModel != null) {
            itemView.txt_news_name.text = photoModel.author
            Glide.with(itemView.context)
                .load(photoModel.downloadUrl)
                .into(itemView.img_news_banner)

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(photoModel)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photoModel: PhotoModel)
    }

    companion object {
        fun create(parent: ViewGroup, onItemClickListener: OnItemClickListener): PhotoViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_photo_item, parent, false)
            return PhotoViewHolder(view, onItemClickListener)
        }
    }
}