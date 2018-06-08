package com.jordantuffery.leboncoin.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.presentation.Constants
import com.jordantuffery.leboncoin.presentation.album_details.AlbumDetailsFragment
import com.jordantuffery.leboncoin.presentation.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.photos.PhotoFragment
import kotlinx.android.synthetic.main.abc_activity_chooser_view_list_item.list_item

class MainActivity : AppCompatActivity(), ToolbarFragment.Listener, AlbumDetailsFragment.Listener{
    /**
     * It's the current album id. It value is NO_ALBUM_ID only if the current fragment isn't a AlbumDetailsFragment.
     */
    private var albumIdToSave: Int? = Constants.NO_ALBUM_ID
    /**
     * It's used to know if the current fragment is the PhotoFragment.
     */
    private var isPhotoFragmentVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recoverLastState(savedInstanceState)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_toolbar,
                         ToolbarFragment.newInstance(albumIdToSave ?: Constants.NO_ALBUM_ID, isPhotoFragmentVisible,
                                                     this))
                .commitAllowingStateLoss()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constants.KEY_ALBUM_ID, albumIdToSave ?: Constants.NO_ALBUM_ID)
        outState.putBoolean(Constants.KEY_IS_PHOTO_FRAGMENT_VISIBLE, isPhotoFragmentVisible)
        super.onSaveInstanceState(outState)
    }

    override fun onFragmentChange(fragment: BaseFragment) {
        val frTransaction = supportFragmentManager.beginTransaction()
        isPhotoFragmentVisible = false
        when (fragment) {
            is PhotoFragment -> isPhotoFragmentVisible = true
            is AlbumDetailsFragment -> fragment.listener = this
        }
        frTransaction.replace(R.id.main_activity_fragment_container, fragment).commitAllowingStateLoss()
    }

    override fun onSaveAlbumId(albumIdToSave: Int) {
        this.albumIdToSave = albumIdToSave
    }

    /**
     * This function is used to recover the saved value of the last instance (if there was a last instance)
     */
    private fun recoverLastState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return

        albumIdToSave = savedInstanceState.getInt(Constants.KEY_ALBUM_ID, Constants.NO_ALBUM_ID)
        isPhotoFragmentVisible = savedInstanceState.getBoolean(Constants.KEY_IS_PHOTO_FRAGMENT_VISIBLE, false)
    }
}
