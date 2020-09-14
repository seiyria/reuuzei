package com.seiyria.reuuzei.data.model

import com.seiyria.reuuzei.BuildConfig
import com.seiyria.reuuzei.REFRESH_GRANT_TYPE

/**
 * Created by Alireza Afkar on 10/16/18.
 */
data class TokenRequest(
    val refresh_token: String,
    val grant_type: String = REFRESH_GRANT_TYPE,
    val client_id: String = BuildConfig.CLIENT_ID
)
