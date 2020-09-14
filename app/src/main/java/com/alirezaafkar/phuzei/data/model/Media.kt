package com.seiyria.reuuzei.data.model

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("id") val id: String,
    @SerializedName("baseUrl") val baseUrl: String,
    @SerializedName("mimeType") val mimeType: String,
    @SerializedName("filename") val filename: String,
    @SerializedName("productUrl") val productUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("mediaMetadata") val metadata: Metadata
)

fun Media.largeUrl() = "$baseUrl=w${metadata.width}-h${metadata.height}"

fun Media.isImage() = mimeType.startsWith("image", true) && !mimeType.endsWith("gif", true)
