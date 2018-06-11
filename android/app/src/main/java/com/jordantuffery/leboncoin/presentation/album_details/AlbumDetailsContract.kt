package com.jordantuffery.leboncoin.presentation.album_details

import com.jordantuffery.leboncoin.api.Album
import com.jordantuffery.leboncoin.presentation.base.BaseContract

interface AlbumDetailsContract : BaseContract {
    interface View : BaseContract.BaseView {
        fun populatePhotoList(album: Album)
    }

    interface Presenter {
        fun requestPhotos(albumId: Int)
    }
}