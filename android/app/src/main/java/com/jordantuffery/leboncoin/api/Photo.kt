package com.jordantuffery.leboncoin.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * POJO for the REST API
 */

@Parcelize
data class Photo(val albumId: Int,
                 val id: Int,
                 val title: String,
                 val url: String,
                 val thumbnailUrl: String) : Parcelable