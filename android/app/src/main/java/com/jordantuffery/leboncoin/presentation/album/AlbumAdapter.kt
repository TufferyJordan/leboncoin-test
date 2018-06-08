package com.jordantuffery.leboncoin.presentation.album

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.presentation.Constants
import kotlinx.android.synthetic.main.item_album_list.view.item_album_list_image_view
import kotlinx.android.synthetic.main.item_album_list.view.item_album_list_text_view

class AlbumAdapter(var adapterList: List<Album>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_list, parent, false))
    }

    override fun getItemCount(): Int = adapterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

    class ViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        val imageView: ImageView = rootView.item_album_list_image_view
        val textView: TextView = rootView.item_album_list_text_view
    }
}
