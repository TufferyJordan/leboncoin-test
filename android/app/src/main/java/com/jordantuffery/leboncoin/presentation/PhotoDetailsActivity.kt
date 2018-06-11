package com.jordantuffery.leboncoin.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Photo
import kotlinx.android.synthetic.main.activity_photo_details.photo_details_activity_button_back
import kotlinx.android.synthetic.main.activity_photo_details.photo_details_activity_description
import kotlinx.android.synthetic.main.activity_photo_details.photo_details_activity_full_image
import kotlinx.android.synthetic.main.activity_photo_details.photo_details_activity_title

class PhotoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        val photo = intent.getParcelableExtra<Photo>(Constants.KEY_PHOTO_PARCELIZED)

        if (intent == null || photo == null) {
            finish()
        }

        Glide.with(this).load(photo.url).into(photo_details_activity_full_image)
        photo_details_activity_title.text = resources.getString(R.string.item_photo_list_title, photo.id)
        photo_details_activity_description.text = photo.title

        photo_details_activity_button_back.setOnClickListener { finish() }
    }
}