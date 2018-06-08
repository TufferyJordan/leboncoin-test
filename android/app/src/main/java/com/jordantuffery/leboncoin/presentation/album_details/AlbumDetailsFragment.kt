package com.jordantuffery.leboncoin.presentation.album_details

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.Constants
import com.jordantuffery.leboncoin.presentation.album.Album
import com.jordantuffery.leboncoin.presentation.photos.PhotoAdapter
import kotlinx.android.synthetic.main.fragment_album_details.album_details_fragment_error
import kotlinx.android.synthetic.main.fragment_album_details.album_details_fragment_progress_bar
import kotlinx.android.synthetic.main.fragment_album_details.album_details_fragment_recycler_view
import kotlinx.android.synthetic.main.fragment_album_details.view.album_details_fragment_recycler_view
import kotlinx.android.synthetic.main.fragment_album_details.view.album_details_fragment_text_view_title

class AlbumDetailsFragment() : BaseFragment(), AlbumDetailsContract.View {
    private var presenter: AlbumDetailsContract.Presenter? = null
    var listener: Listener? = null

    private var photoAdapter = PhotoAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(
                R.layout.fragment_album_details, container, false)

        rootView.album_details_fragment_recycler_view.adapter = photoAdapter
        rootView.album_details_fragment_recycler_view.layoutManager =
                if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager(context, 3)
                } else {
                    GridLayoutManager(context, 2)
                }
        rootView.album_details_fragment_text_view_title.text = rootView.resources.getString(
                R.string.item_album_list_title, arguments?.getInt(Constants.KEY_ALBUM_ID) ?: 0)
        listener?.onSaveAlbumId(arguments?.getInt(Constants.KEY_ALBUM_ID) ?: Constants.NO_ALBUM_ID)

        return rootView
    }

    override fun onStart() {
        presenter = AlbumDetailsPresenterImpl(api, this)
        presenter?.requestPhotos(arguments?.getInt(Constants.KEY_ALBUM_ID) ?: 0)
        super.onStart()
    }

    override fun onStop() {
        presenter = null
        super.onStop()
    }

    override fun onDestroyView() {
        listener?.onSaveAlbumId(Constants.NO_ALBUM_ID)
        super.onDestroyView()
    }

    override fun showProgress() {
        album_details_fragment_recycler_view.visibility = View.GONE
        album_details_fragment_progress_bar.visibility = View.VISIBLE
        album_details_fragment_error.visibility = View.GONE
    }

    override fun hideProgress() {
        album_details_fragment_recycler_view.visibility = View.VISIBLE
        album_details_fragment_progress_bar.visibility = View.GONE
        album_details_fragment_error.visibility = View.GONE
    }

    override fun populatePhotoList(album: Album) {
        photoAdapter.adapterList = album.photos
        photoAdapter.notifyDataSetChanged()
    }

    override fun showError() {
        album_details_fragment_recycler_view.visibility = View.GONE
        album_details_fragment_progress_bar.visibility = View.GONE
        album_details_fragment_error.visibility = View.VISIBLE
    }

    interface Listener {
        fun onSaveAlbumId(albumIdToSave: Int)
    }

    companion object {
        fun newInstance(albumId: Int): AlbumDetailsFragment {
            val fragment = AlbumDetailsFragment()
            val args = Bundle().apply { putInt(Constants.KEY_ALBUM_ID, albumId) }
            fragment.arguments = args
            return fragment
        }

    }
}
