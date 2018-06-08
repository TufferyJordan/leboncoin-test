package com.jordantuffery.leboncoin.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class Api(private val apiService: ApiService) {
    fun getPhotoList(callback: GetPhotoListCallback) {
        apiService.getPhotoList().enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Timber.e(t)
                callback.onError()
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.body() == null) {
                    // when the cache is readied but nothing is currently saved
                    callback.onError()
                } else {
                    callback.onSuccess(response.body()!!)
                }
            }
        })
    }

    interface GetPhotoListCallback {
        fun onSuccess(photoList: List<Photo>)
        fun onError()
    }
}