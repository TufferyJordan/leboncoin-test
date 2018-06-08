package com.jordantuffery.leboncoin.presentation.photos

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Photo
import com.jordantuffery.leboncoin.presentation.Constants
import com.jordantuffery.leboncoin.presentation.PhotoDetailsActivity
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
            val context = holder.rootView.context
            holder.rootView.setOnClickListener {
                context.startActivity(Intent(context, PhotoDetailsActivity::class.java).apply {
                    putExtra(Constants.KEY_PHOTO_PARCELIZED, adapterList[position])
                })
            }
        }
    }

    class ViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        val imageView: ImageView = rootView.item_photo_list_image_view
        val textView: TextView = rootView.item_photo_list_text_view
    }

    companion object {
    }
}
