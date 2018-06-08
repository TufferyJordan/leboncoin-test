package com.jordantuffery.leboncoin.presentation.album

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Album
import com.jordantuffery.leboncoin.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_album.album_fragment_error
import kotlinx.android.synthetic.main.fragment_album.album_fragment_progress_bar
import kotlinx.android.synthetic.main.fragment_album.album_fragment_recycler_view
import kotlinx.android.synthetic.main.fragment_album.view.album_fragment_error
import kotlinx.android.synthetic.main.fragment_album.view.album_fragment_recycler_view

class AlbumFragment : BaseFragment(), AlbumContract.AlbumView {

    private var presenter: AlbumContract.AlbumPresenter? = null

    private var albumAdapter = AlbumAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(
                R.layout.fragment_album, container, false)

        rootView.album_fragment_recycler_view.adapter = albumAdapter
        rootView.album_fragment_recycler_view.layoutManager =
                if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager(context, 3)
                } else {
                    GridLayoutManager(context, 2)
                }

        rootView.album_fragment_error.setOnClickListener { presenter?.requestAlbums() }

        return rootView
    }

    override fun onStart() {

        presenter = AlbumPresenterImpl(api, this)
        presenter?.requestAlbums()
        super.onStart()
    }

    override fun onStop() {
        presenter = null
        super.onStop()
    }


    override fun showProgress() {
        album_fragment_recycler_view.visibility = View.GONE
        album_fragment_progress_bar.visibility = View.VISIBLE
        album_fragment_error.visibility = View.GONE
    }

    override fun hideProgress() {
        album_fragment_recycler_view.visibility = View.VISIBLE
        album_fragment_progress_bar.visibility = View.GONE
        album_fragment_error.visibility = View.GONE
    }

    override fun populateAlbumList(albumList: List<Album>) {
        albumAdapter.adapterList = albumList
        albumAdapter.notifyDataSetChanged()
    }

    override fun showError() {
        album_fragment_recycler_view.visibility = View.GONE
        album_fragment_progress_bar.visibility = View.GONE
        album_fragment_error.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(): AlbumFragment = AlbumFragment()
    }
}
