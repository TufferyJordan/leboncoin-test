package com.jordantuffery.leboncoin.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.presentation.album.AlbumAdapter
import com.jordantuffery.leboncoin.presentation.album.AlbumFragment
import com.jordantuffery.leboncoin.presentation.album_details.AlbumDetailsFragment
import com.jordantuffery.leboncoin.presentation.photos.PhotoFragment
import kotlinx.android.synthetic.main.activity_main.main_activity_button_album
import kotlinx.android.synthetic.main.activity_main.main_activity_button_back
import kotlinx.android.synthetic.main.activity_main.main_activity_button_photo

class MainActivity : AppCompatActivity() {

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.apply {
                when (action) {
                    AlbumAdapter.EVENT_CLICK_ALBUM -> {
                        val albumId = getIntExtra(AlbumAdapter.KEY_ALBUM_ID, 0)
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.main_activity_fragment_container,
                                         AlbumDetailsFragment.newInstance(albumId)).commitAllowingStateLoss()

                        main_activity_button_album.visibility = View.GONE
                        main_activity_button_back.visibility = View.VISIBLE
                        main_activity_button_back.setOnClickListener(onBackButtonClickListener)
                    }
                }
            }
        }
    }

    private val onBackButtonClickListener: (View) -> Unit = {
        supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container,
                                                          AlbumFragment.newInstance()).commitAllowingStateLoss()
        main_activity_button_back.visibility = View.INVISIBLE
        main_activity_button_album.visibility = View.VISIBLE
        main_activity_button_back.setOnClickListener(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter(AlbumAdapter.EVENT_CLICK_ALBUM))

        supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container,
                                                          AlbumFragment.newInstance()).commitAllowingStateLoss()
        main_activity_button_back.setOnClickListener(onBackButtonClickListener)
        main_activity_button_album.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container,
                                                              AlbumFragment.newInstance()).commitAllowingStateLoss()
        }
        main_activity_button_photo.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container,
                                                              PhotoFragment.newInstance()).commit()
            main_activity_button_back.visibility = View.INVISIBLE
            main_activity_button_album.visibility = View.VISIBLE
            main_activity_button_back.setOnClickListener(null)
        }
    }
}
