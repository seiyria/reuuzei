package com.seiyria.reuuzei.data.repository

import com.seiyria.reuuzei.data.api.TokenApi
import com.seiyria.reuuzei.data.pref.AppPreferences
import com.seiyria.reuuzei.util.applyNetworkSchedulers
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Alireza Afkar on 16/9/2018AD.
 */
class TokenRepository @Inject constructor(
    private var api: TokenApi,
    private var prefs: AppPreferences
) {
    fun request(code: String): Single<Unit> {
        return api.request(code).map {
            prefs.tokenType = it.tokenType
            prefs.accessToken = it.accessToken
            it.refreshToken?.let { token ->
                prefs.refreshToken = token
            }
        }.compose(applyNetworkSchedulers())
    }
}
