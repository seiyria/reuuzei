package com.seiyria.reuuzei.data.repository

import com.seiyria.reuuzei.data.api.AlbumsApi
import com.seiyria.reuuzei.data.model.Album
import com.seiyria.reuuzei.data.model.AlbumsResponse
import com.seiyria.reuuzei.data.model.SharedAlbumsResponse
import com.seiyria.reuuzei.util.applyNetworkSchedulers
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Alireza Afkar on 16/9/2018AD.
 */
class AlbumsRepository @Inject constructor(private var api: AlbumsApi) {
    fun getAlbums(pageToken: String? = null): Single<AlbumsResponse> {
        return api.getAlbums(pageToken).compose(applyNetworkSchedulers())
    }

    fun getSharedAlbums(pageToken: String? = null): Single<SharedAlbumsResponse> {
        return api.getSharedAlbums(pageToken).compose(applyNetworkSchedulers())
    }

    fun getAlbum(albumId: String): Single<Album> {
        return api.getAlbum(albumId).compose(applyNetworkSchedulers())
    }
}
