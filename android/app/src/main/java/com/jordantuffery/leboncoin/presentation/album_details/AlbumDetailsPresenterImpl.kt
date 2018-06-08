package com.jordantuffery.leboncoin.presentation.album_details

import com.jordantuffery.leboncoin.api.Album
import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.api.Photo

class AlbumDetailsPresenterImpl(val api: Api,
                                val view: AlbumDetailsContract.View) : AlbumDetailsContract.Presenter {
    override fun requestPhotos(albumId: Int) {
        view.showProgress()
        api.getPhotoList(object : Api.GetPhotoListCallback {
            override fun onError() {
                view.hideProgress()
                view.showError()
            }

            override fun onSuccess(photoList: List<Photo>) {
                val album = Album.fromAlbumId(albumId, photoList)
                view.hideProgress()
                view.populatePhotoList(album)
            }
        })
    }
}