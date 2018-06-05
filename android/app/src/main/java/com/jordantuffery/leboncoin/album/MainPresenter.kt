package com.jordantuffery.leboncoin.album

import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.api.Photo

class MainPresenter(val api: Api,
                    val view: MainContract.MainView) : MainContract.MainPresenter {
    override fun requestPhotoList() {
        view.showProgress()
        api.getPhotoList(object : Api.GetPhotoListCallback {
            override fun onError(error: Throwable?) {
                view.showError(error?.message)
            }

            override fun onSuccess(photoList: List<Photo>?) {
                view.hideProgress()
                if (photoList == null) {
                    view.showError(NOTHING_CACHED)
                } else {
                    view.populatePhotoList(photoList)
                }
            }
        })
    }

    companion object {
        const val NOTHING_CACHED = "Nothing is currently cached and the server is unreachable"
    }
}