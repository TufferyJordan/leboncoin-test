package com.jordantuffery.leboncoin.presentation.main

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.album_details.AlbumDetailsFragment
import com.jordantuffery.leboncoin.presentation.photos.PhotoFragment

class MainActivity : AppCompatActivity(), ToolbarFragment.Listener, AlbumDetailsFragment.Listener {
    private var albumIdToSave = AlbumDetailsFragment.NO_ALBUM_ID
    private var isPhotoFragmentVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val albumIdSaved = savedInstanceState?.getInt(AlbumDetailsFragment.KEY_ALBUM_ID,
                                                      AlbumDetailsFragment.NO_ALBUM_ID)
        if (albumIdSaved != null && albumIdSaved != AlbumDetailsFragment.NO_ALBUM_ID) {
            albumIdToSave = albumIdSaved
        }

        val isPhotoFragmentVisibleSaved = savedInstanceState?.getBoolean(KEY_IS_PHOTO_FRAGMENT_VISIBLE,
                                                                false)
        if(isPhotoFragmentVisibleSaved != null && isPhotoFragmentVisibleSaved) {
            isPhotoFragmentVisible = isPhotoFragmentVisibleSaved
        }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_toolbar, ToolbarFragment.newInstance(albumIdToSave, isPhotoFragmentVisible,
                                                                                 this))
                .commitAllowingStateLoss()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (albumIdToSave != AlbumDetailsFragment.NO_ALBUM_ID) {
            outState.putInt(AlbumDetailsFragment.KEY_ALBUM_ID, albumIdToSave)
        }
        outState.putBoolean(KEY_IS_PHOTO_FRAGMENT_VISIBLE, isPhotoFragmentVisible)
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

    companion object {
        const val KEY_IS_PHOTO_FRAGMENT_VISIBLE = "KEY_IS_PHOTO_FRAGMENT_VISIBLE"
    }
}
