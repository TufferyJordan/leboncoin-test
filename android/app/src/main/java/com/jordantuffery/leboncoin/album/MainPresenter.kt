package com.jordantuffery.leboncoin.album

import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.api.Photo

class MainPresenter(val api: Api,
                    val view: MainContract.MainView) : MainContract.MainPresenter {
    /**
     * asynchronous call to the api rest
     */
    override fun requestPhotoList() {
        view.showProgress()
        api.getPhotoList(object : Api.GetPhotoListCallback {
            override fun onError() {
                view.hideProgress()
                view.showError()
            }

            override fun onSuccess(photoList: List<Photo>) {
                view.hideProgress()
                view.populatePhotoList(photoList)
            }
        })
    }
}