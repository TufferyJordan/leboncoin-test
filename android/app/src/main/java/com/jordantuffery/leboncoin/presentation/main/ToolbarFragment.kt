package com.jordantuffery.leboncoin.presentation.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.album.AlbumAdapter
import com.jordantuffery.leboncoin.presentation.album.AlbumFragment
import com.jordantuffery.leboncoin.presentation.album_details.AlbumDetailsFragment
import com.jordantuffery.leboncoin.presentation.photos.PhotoFragment
import kotlinx.android.synthetic.main.fragment_toolbar.main_activity_button_album
import kotlinx.android.synthetic.main.fragment_toolbar.main_activity_button_back
import kotlinx.android.synthetic.main.fragment_toolbar.main_activity_button_photo

class ToolbarFragment : Fragment() {
    private var listener: Listener? = null

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.apply {
                when (action) {
                    AlbumAdapter.EVENT_CLICK_ALBUM -> {
                        val albumId = getIntExtra(AlbumAdapter.KEY_ALBUM_ID, 0)
                        goToAlbumDetails(albumId)
                    }
                }
            }
        }
    }
    private val onBackButtonClickListener: (View) -> Unit = { goToAlbum() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(
            R.layout.fragment_toolbar, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LocalBroadcastManager.getInstance(view.context).registerReceiver(receiver,
                                                                         IntentFilter(AlbumAdapter.EVENT_CLICK_ALBUM))

        if (arguments == null || arguments?.getInt(KEY_ALBUM_ID,
                                                   AlbumDetailsFragment.NO_ALBUM_ID) == AlbumDetailsFragment.NO_ALBUM_ID) {
            goToAlbum()
        } else {
            goToAlbumDetails(arguments!!.getInt(KEY_ALBUM_ID, AlbumDetailsFragment.NO_ALBUM_ID))
        }

        main_activity_button_back.setOnClickListener(onBackButtonClickListener)
        main_activity_button_album.setOnClickListener { goToAlbum() }
        main_activity_button_photo.setOnClickListener { goToPhoto() }

    }

    override fun onDestroyView() {
        LocalBroadcastManager.getInstance(activity!!).unregisterReceiver(receiver)
        super.onDestroyView()
    }

    private fun goToAlbum() {
        listener?.onFragmentChange(AlbumFragment.newInstance())
        main_activity_button_back.visibility = View.INVISIBLE
        main_activity_button_album.visibility = View.VISIBLE
        main_activity_button_back.setOnClickListener(null)
    }

    private fun goToAlbumDetails(albumId: Int) {
        listener?.onFragmentChange(AlbumDetailsFragment.newInstance(albumId))

        main_activity_button_album.visibility = View.GONE
        main_activity_button_back.visibility = View.VISIBLE
        main_activity_button_back.setOnClickListener(onBackButtonClickListener)
    }

    private fun goToPhoto() {
        listener?.onFragmentChange(PhotoFragment.newInstance())
        main_activity_button_back.visibility = View.INVISIBLE
        main_activity_button_album.visibility = View.VISIBLE
        main_activity_button_back.setOnClickListener(null)
    }

    interface Listener {
        fun onFragmentChange(fragment: BaseFragment)
    }

    companion object {
        fun newInstance(albumId: Int, listener: Listener): ToolbarFragment {
            val fragment = ToolbarFragment()
            val bundle = Bundle().apply {
                putInt(KEY_ALBUM_ID, albumId)
            }
            fragment.listener = listener
            fragment.arguments = bundle
            return fragment
        }

        private const val KEY_ALBUM_ID = "KEY_ALBUM_ID"
    }
}