package com.jordantuffery.leboncoin.api

/**
 * POJO for the REST API
 */
data class Photo(val albumId: Int,
                 val id: Int,
                 val title: String,
                 val url: String,
                 val thumbnailUrl: String)