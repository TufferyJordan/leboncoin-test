package com.jordantuffery.leboncoin.presentation.main

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.presentation.Constants
import com.jordantuffery.leboncoin.presentation.album_details.AlbumDetailsFragment
import com.jordantuffery.leboncoin.presentation.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.photos.PhotoFragment

class MainActivity : AppCompatActivity(), ToolbarFragment.Listener, AlbumDetailsFragment.Listener {
    private var albumIdToSave = Constants.NO_ALBUM_ID
    private var isPhotoFragmentVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val albumIdSaved = savedInstanceState?.getInt(Constants.KEY_ALBUM_ID,
                                                      Constants.NO_ALBUM_ID)
        if (albumIdSaved != null) {
            albumIdToSave = albumIdSaved
        }

        val isPhotoFragmentVisibleSaved = savedInstanceState?.getBoolean(Constants.KEY_IS_PHOTO_FRAGMENT_VISIBLE,
                                                                         false)
        if (isPhotoFragmentVisibleSaved != null) {
            isPhotoFragmentVisible = isPhotoFragmentVisibleSaved
        }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_toolbar, ToolbarFragment.newInstance(albumIdToSave, isPhotoFragmentVisible,
                                                                                 this))
                .commitAllowingStateLoss()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constants.KEY_ALBUM_ID, albumIdToSave)
        outState.putBoolean(Constants.KEY_IS_PHOTO_FRAGMENT_VISIBLE, isPhotoFragmentVisible)
        super.onSaveInstanceState(outState)
    }

    override fun onSaveAlbumId(albumIdToSave: Int) {
        this.albumIdToSave = albumIdToSave
    }

    override fun onFragmentChange(fragment: BaseFragment) {
        val frTransaction = supportFragmentManager.beginTransaction()
        isPhotoFragmentVisible = false
        when (fragment) {
            is AlbumDetailsFragment -> fragment.listener = this
            is PhotoFragment -> isPhotoFragmentVisible = true
        }
        frTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        frTransaction.replace(R.id.main_activity_fragment_container, fragment).commitAllowingStateLoss()
    }
}
