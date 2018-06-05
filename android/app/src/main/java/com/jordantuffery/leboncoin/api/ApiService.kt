package com.jordantuffery.leboncoin.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    fun getPhotoList(): Call<List<Photo>>
}