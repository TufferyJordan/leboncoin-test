package com.jordantuffery.leboncoin.presentation.photos

import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.api.Photo

class PhotoPresenterImpl(val api: Api,
                         val view: PhotoContract.PhotoView) : PhotoContract.PhotoPresenter {
    override fun requestPhotos() {
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