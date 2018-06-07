package com.jordantuffery.leboncoin.presentation.album

import com.jordantuffery.leboncoin.api.Photo

data class Album(val id: Int,
                 val photos: ArrayList<Photo>) {
    companion object {
        fun createAlbumListFromPhotoList(photoList: List<Photo>): List<Album> {
            val albumsList = arrayListOf<Album>()
            var lastAlbum = 0
            for (photo in photoList) {
                if (photo.albumId == lastAlbum) {
                    albumsList.last().photos.add(photo)
                } else {
                    albumsList.add(Album(photo.albumId, arrayListOf(photo)))
                    lastAlbum = photo.albumId
                }
            }
            return albumsList
        }

        fun fromAlbumId(albumId: Int, photoList: List<Photo>): Album = Album(albumId,
                                                                             photoList.filter { it.albumId == albumId } as ArrayList<Photo>)
    }
}