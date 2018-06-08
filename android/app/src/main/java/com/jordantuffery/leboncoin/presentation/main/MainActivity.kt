package com.jordantuffery.leboncoin.presentation.main

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.album.AlbumFragment
import com.jordantuffery.leboncoin.presentation.album_details.AlbumDetailsFragment

class MainActivity : AppCompatActivity(), ToolbarFragment.Listener, AlbumDetailsFragment.Listener {
    private var albumIdToSave = AlbumDetailsFragment.NO_ALBUM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var albumId = AlbumDetailsFragment.NO_ALBUM_ID
        val albumIdSaved = savedInstanceState?.getInt(AlbumDetailsFragment.KEY_ALBUM_ID,
                                                      AlbumDetailsFragment.NO_ALBUM_ID)
        if (albumIdSaved != null && albumIdSaved != AlbumDetailsFragment.NO_ALBUM_ID) {
            albumId = albumIdSaved
        }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_toolbar, ToolbarFragment.newInstance(albumId,
                                                                                 this))
                .commitAllowingStateLoss()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (albumIdToSave != AlbumDetailsFragment.NO_ALBUM_ID) {
            outState.putInt(AlbumDetailsFragment.KEY_ALBUM_ID, albumIdToSave)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onSaveAlbumId(albumIdToSave: Int) {
        this.albumIdToSave = albumIdToSave
    }

    override fun onFragmentChange(fragment: BaseFragment) {
        val frTransaction = supportFragmentManager.beginTransaction()
        when (fragment) {
            is AlbumDetailsFragment -> {
                frTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragment.listener = this
            }
            is AlbumFragment -> frTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }
        frTransaction.replace(R.id.main_activity_fragment_container, fragment).commitAllowingStateLoss()
    }
}
