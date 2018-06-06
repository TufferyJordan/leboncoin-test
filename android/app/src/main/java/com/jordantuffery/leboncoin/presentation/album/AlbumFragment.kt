package com.jordantuffery.leboncoin.presentation.album

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordantuffery.leboncoin.BaseFragment
import com.jordantuffery.leboncoin.R
import kotlinx.android.synthetic.main.fragment_album.album_fragment_error
import kotlinx.android.synthetic.main.fragment_album.album_fragment_progress_bar
import kotlinx.android.synthetic.main.fragment_album.album_fragment_recycler_view

class AlbumFragment : BaseFragment(), AlbumContract.AlbumView {

    private var presenter: AlbumContract.AlbumPresenter? = null

    private var albumAdapter = AlbumAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(
            R.layout.fragment_album, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        album_fragment_recycler_view.adapter = albumAdapter
        album_fragment_recycler_view.layoutManager = GridLayoutManager(view.context, 2)

        album_fragment_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    presenter?.lastItemIndex = (recyclerView.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
                }
            }
        })

        presenter = AlbumPresenterImpl(api, this)
        presenter?.requestAlbums()

        recoverLastState(savedInstanceState)
    }

    private fun recoverLastState(savedInstanceState: Bundle?) {
        val lastSavedItemPosition = savedInstanceState?.getInt(CURRENT_ITEM_POSITION, -1)
        presenter?.lastItemIndex = lastSavedItemPosition
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val currentItemPosition = presenter?.lastItemIndex
        outState.putInt(CURRENT_ITEM_POSITION, currentItemPosition!!)
        super.onSaveInstanceState(outState)
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

    override fun restoreLastItemIndex(itemIndex: Int) {
        album_fragment_recycler_view.scrollToPosition(itemIndex)
    }

    companion object {
        fun newInstance(): AlbumFragment {
            return AlbumFragment()
        }

        private const val CURRENT_ITEM_POSITION = "CURRENT_ITEM_POSITION"
    }
}
