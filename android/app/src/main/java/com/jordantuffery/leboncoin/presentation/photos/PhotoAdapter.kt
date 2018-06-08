package com.jordantuffery.leboncoin.presentation.photos

import android.content.Intent
import com.bumptech.glide.Glide
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Photo
import com.jordantuffery.leboncoin.presentation.Constants
import com.jordantuffery.leboncoin.presentation.PhotoDetailsActivity
import com.jordantuffery.leboncoin.presentation.base.BaseAdapter

class PhotoAdapter(adapterList: List<Photo>) : BaseAdapter<Photo>(adapterList) {
    override fun bindHolder(holder: BaseAdapter.ViewHolder, position: Int) {
        adapterList[position].apply {
            Glide.with(holder.rootView).load(thumbnailUrl).into(holder.imageView)
            holder.textView.text = String.format(
                    holder.rootView.context.resources.getString(R.string.item_photo_list_title), id)
            val context = holder.rootView.context
            holder.rootView.setOnClickListener {
                context.startActivity(Intent(context, PhotoDetailsActivity::class.java).apply {
                    putExtra(Constants.KEY_PHOTO_PARCELIZED, adapterList[position])
                })
            }
        }
    }
}
