package com.jordantuffery.leboncoin.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.presentation.album.AlbumFragment
import com.jordantuffery.leboncoin.presentation.photos.PhotoFragment
import kotlinx.android.synthetic.main.activity_main.main_activity_button_album
import kotlinx.android.synthetic.main.activity_main.main_activity_button_photo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container,
                                                          AlbumFragment.newInstance()).commit()
        main_activity_button_album.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container,
                                                              AlbumFragment.newInstance()).commit()
        }
        main_activity_button_photo.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container,
                                                              PhotoFragment.newInstance()).commit()
        }
    }


}
