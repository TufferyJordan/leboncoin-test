package com.jordantuffery.leboncoin.presentation.album

interface AlbumContract {
    interface AlbumView {

        fun showProgress()
        fun hideProgress()
        fun populateAlbumList(albumList: List<Album>)
        fun showError()
        fun restoreLastItemIndex(itemIndex: Int)
    }

    interface AlbumPresenter {
        var lastItemIndex: Int?

        fun requestAlbums()
    }
}