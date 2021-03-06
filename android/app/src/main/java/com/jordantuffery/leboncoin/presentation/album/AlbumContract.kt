package com.jordantuffery.leboncoin.presentation.album

import com.jordantuffery.leboncoin.api.Album
import com.jordantuffery.leboncoin.presentation.base.BaseContract

interface AlbumContract : BaseContract {
    interface AlbumView : BaseContract.BaseView {
        fun populateAlbumList(albumList: List<Album>)
    }

    interface AlbumPresenter {
        fun requestAlbums()
    }
}