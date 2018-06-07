package com.jordantuffery.leboncoin.presentation.album_details

import com.jordantuffery.leboncoin.base.BaseContract
import com.jordantuffery.leboncoin.presentation.album.Album

interface AlbumDetailsContract : BaseContract {
    interface View : BaseContract.BaseView {
        fun populatePhotoList(album: Album)
    }

    interface Presenter {
        fun requestPhotos(albumId: Int)
    }
}