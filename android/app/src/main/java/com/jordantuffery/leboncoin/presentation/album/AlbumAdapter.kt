package com.jordantuffery.leboncoin.presentation.album

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jordantuffery.leboncoin.R
import kotlinx.android.synthetic.main.item_album_list.view.item_album_list_image_view
import kotlinx.android.synthetic.main.item_album_list.view.item_album_list_text_view

class AlbumAdapter(var adapterList: List<Album>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(parent.context, R.layout.item_album_list, null))
    }

    override fun getItemCount(): Int = adapterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapterList[position].apply {
            Glide.with(holder.rootView).load(photos[0].thumbnailUrl).into(holder.imageView)
            holder.textView.text = String.format(
                    holder.rootView.context.resources.getString(R.string.item_album_list_title), id)
        }
    }

    class ViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        val imageView = rootView.item_album_list_image_view
        val textView = rootView.item_album_list_text_view
    }
}
