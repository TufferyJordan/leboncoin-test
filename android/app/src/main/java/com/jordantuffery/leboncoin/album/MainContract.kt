package com.jordantuffery.leboncoin.album

import com.jordantuffery.leboncoin.api.Photo

interface MainContract {
    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun populatePhotoList(photoList: List<Photo>)
        fun showError(message: String? = null)
    }

    interface MainPresenter {
        fun requestPhotoList()
    }
}