package com.jordantuffery.leboncoin.api

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * POJO for the REST API
 */

@Parcelize
@SuppressLint("ParcelCreator")
data class Photo(val albumId: Int,
                 val id: Int,
                 val title: String,
                 val url: String,
                 val thumbnailUrl: String) : Parcelable