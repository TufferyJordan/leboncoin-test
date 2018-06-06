package com.jordantuffery.leboncoin.presentation

import com.jordantuffery.leboncoin.api.Photo

interface MainContract {
    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun populatePhotoList(photoList: List<Photo>)
        fun showError()
    }

    interface MainPresenter {
        fun requestPhotoList()
    }
}