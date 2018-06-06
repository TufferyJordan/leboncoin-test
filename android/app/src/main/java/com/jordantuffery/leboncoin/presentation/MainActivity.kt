package com.jordantuffery.leboncoin.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.presentation.album.AlbumFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.main_activity_fragment_container, AlbumFragment.newInstance())
                .commit()
    }
}
