package com.jordantuffery.leboncoin.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Api(private val apiService: ApiService) {
    fun getPhotoList(callback: GetPhotoListCallback) {
        apiService.getPhotoList().enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                callback.onSuccess(response.body())
            }
        })
    }

    interface GetPhotoListCallback {
        fun onSuccess(photoList: List<Photo>?)
        fun onError(error: Throwable?)
    }
}