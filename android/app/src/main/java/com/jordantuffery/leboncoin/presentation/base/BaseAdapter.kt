package com.jordantuffery.leboncoin.presentation.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jordantuffery.leboncoin.R
import kotlinx.android.synthetic.main.item_recycler_list.view.item_recycler_list_image_view
import kotlinx.android.synthetic.main.item_recycler_list.view.item_recycler_list_text_view

abstract class BaseAdapter<T>(var adapterList: List<T>) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_list, parent, false))
    }

    override fun getItemCount(): Int = adapterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindHolder(holder, position)
    }

    abstract fun bindHolder(holder: ViewHolder, position: Int)

    class ViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        val imageView: ImageView = rootView.item_recycler_list_image_view
        val textView: TextView = rootView.item_recycler_list_text_view
    }
}
