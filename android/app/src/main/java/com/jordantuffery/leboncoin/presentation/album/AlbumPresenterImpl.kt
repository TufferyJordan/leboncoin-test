package com.jordantuffery.leboncoin.presentation.album

import com.jordantuffery.leboncoin.api.Album
import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.api.Photo

class AlbumPresenterImpl(val api: Api,
                         val view: AlbumContract.AlbumView) : AlbumContract.AlbumPresenter {
    /**
     * asynchronous call to the api rest
     */
    override fun requestAlbums() {
        view.showProgress()
        api.getPhotoList(object : Api.GetPhotoListCallback {
            override fun onError() {
                view.hideProgress()
                view.showError()
            }

            override fun onSuccess(photoList: List<Photo>) {
                val albumList = Album.createAlbumListFromPhotoList(photoList)
                view.hideProgress()
                view.populateAlbumList(albumList)
            }
        })
    }
}