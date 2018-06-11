package com.jordantuffery.leboncoin.presentation.album

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Album
import com.jordantuffery.leboncoin.presentation.Constants
import com.jordantuffery.leboncoin.presentation.base.BaseAdapter

class AlbumAdapter(adapterList: List<Album>) : BaseAdapter<Album>(adapterList) {
    override fun bindHolder(holder: BaseAdapter.ViewHolder, position: Int) {
        adapterList[position].apply {
            Glide.with(holder.rootView).load(photos[0].thumbnailUrl).into(holder.imageView)
            holder.textView.text = String.format(
                    holder.rootView.context.resources.getString(R.string.item_album_list_title), id)

            holder.rootView.setOnClickListener {
                val intent = Intent().apply {
                    action = Constants.EVENT_CLICK_ALBUM
                    putExtra(Constants.KEY_ALBUM_ID, id)
                }
                LocalBroadcastManager.getInstance(holder.rootView.context).sendBroadcast(intent)
            }
        }
    }
}
