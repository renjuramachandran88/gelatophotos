package com.gelatotest.gelatophotos.photolist

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gelatotest.gelatophotos.GelatoPhotosApplication
import com.gelatotest.gelatophotos.PhotoModel
import com.gelatotest.gelatophotos.R
import com.gelatotest.gelatophotos.State.*
import com.gelatotest.gelatophotos.photoview.PhotoViewActivity
import kotlinx.android.synthetic.main.activity_photo_list.*
import javax.inject.Inject


class PhotoListActivity : AppCompatActivity(), LifecycleOwner, PhotoViewHolder.OnItemClickListener {
    private lateinit var photoListViewModel: PhotoListViewModel
    private lateinit var viewDataBinding: ViewDataBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var adapter: PhotoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as GelatoPhotosApplication).appComponent
            .inject(this)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list)
        photoListViewModel =
            ViewModelProvider(this, viewModelFactory).get(PhotoListViewModel::class.java)

        initAdapter()
        initState()
    }

    private fun initAdapter() {
        adapter = PhotoListAdapter { photoListViewModel.retry() }
        val gridLayoutManager = GridLayoutManager(applicationContext, 3)
        recycler_view.layoutManager = gridLayoutManager
        recycler_view.adapter = adapter
        adapter.setOnItemClickListener(this)
        photoListViewModel.photoList.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initState() {
        txt_error.setOnClickListener { photoListViewModel.retry() }
        photoListViewModel.getState().observe(this, { state ->
            progress_bar.visibility =
                if (photoListViewModel.listIsEmpty() && state == LOADING) VISIBLE else GONE
            txt_error.visibility =
                if (photoListViewModel.listIsEmpty() && state == ERROR) VISIBLE else GONE
            if (!photoListViewModel.listIsEmpty()) {
                adapter.setState(state ?: DONE)
            }
        })
    }

    override fun onItemClick(photoModel: PhotoModel) {
        PhotoViewActivity.startInstance(this, photoModel)
    }
}