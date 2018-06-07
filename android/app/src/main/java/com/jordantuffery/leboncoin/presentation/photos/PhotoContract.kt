package com.jordantuffery.leboncoin.presentation.photos

import com.jordantuffery.leboncoin.api.Photo
import com.jordantuffery.leboncoin.base.BaseContract

interface PhotoContract : BaseContract {
    interface PhotoView : BaseContract.BaseView {
        fun populatePhotoList(photoList: List<Photo>)
    }

    interface PhotoPresenter {
        fun requestPhotos()
    }
}