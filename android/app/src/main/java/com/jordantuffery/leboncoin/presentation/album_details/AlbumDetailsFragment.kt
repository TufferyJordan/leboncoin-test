package com.jordantuffery.leboncoin.presentation.album_details

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Photo
import com.jordantuffery.leboncoin.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.photos.PhotoAdapter
import kotlinx.android.synthetic.main.fragment_photo.photo_fragment_error
import kotlinx.android.synthetic.main.fragment_photo.photo_fragment_progress_bar
import kotlinx.android.synthetic.main.fragment_photo.photo_fragment_recycler_view
import kotlinx.android.synthetic.main.fragment_photo.view.photo_fragment_recycler_view

class AlbumDetailsFragment : BaseFragment(), AlbumDetailsContract.View{

    private var presenter: AlbumDetailsContract.Presenter? = null

    private var photoAdapter = PhotoAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(
                R.layout.fragment_photo, container, false)

        rootView.photo_fragment_recycler_view.adapter = photoAdapter
        rootView.photo_fragment_recycler_view.layoutManager =
                if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager(context, 3)
                } else {
                    GridLayoutManager(context, 2)
                }

        return rootView
    }

    override fun onStart() {
        presenter = AlbumDetailsPresenterImpl(api, this)
        presenter?.requestPhotos()
        super.onStart()
    }

    override fun onStop() {
        presenter = null
        super.onStop()
    }


    override fun showProgress() {
        photo_fragment_recycler_view.visibility = View.GONE
        photo_fragment_progress_bar.visibility = View.VISIBLE
        photo_fragment_error.visibility = View.GONE
    }

    override fun hideProgress() {
        photo_fragment_recycler_view.visibility = View.VISIBLE
        photo_fragment_progress_bar.visibility = View.GONE
        photo_fragment_error.visibility = View.GONE
    }

    override fun populatePhotoList(photoList: List<Photo>) {
        photoAdapter.adapterList = photoList
        photoAdapter.notifyDataSetChanged()
    }

    override fun showError() {
        photo_fragment_recycler_view.visibility = View.GONE
        photo_fragment_progress_bar.visibility = View.GONE
        photo_fragment_error.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(): AlbumDetailsFragment = AlbumDetailsFragment()
    }
}
