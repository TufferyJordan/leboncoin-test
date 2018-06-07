package com.jordantuffery.leboncoin.presentation.photos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Photo
import kotlinx.android.synthetic.main.item_photo_list.view.item_photo_list_image_view
import kotlinx.android.synthetic.main.item_photo_list.view.item_photo_list_text_view

class PhotoAdapter(var adapterList: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo_list, parent, false))
    }

    override fun getItemCount(): Int = adapterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapterList[position].apply {
            Glide.with(holder.rootView).load(thumbnailUrl).into(holder.imageView)
            holder.textView.text = String.format(
                    holder.rootView.context.resources.getString(R.string.item_photo_list_title), id)
        }
    }

    class ViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        val imageView = rootView.item_photo_list_image_view
        val textView = rootView.item_photo_list_text_view
    }
}
