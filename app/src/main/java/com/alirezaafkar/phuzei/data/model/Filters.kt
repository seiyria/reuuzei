package com.seiyria.reuuzei.data.model

/**
 * Created by Alireza Afkar on 5/21/20.
 */
data class Filters(
    val featureFilter: FeatureFilter? = null,
    val contentFilter: ContentFilter? = null
)

data class FeatureFilter(val includedFeatures: List<String>)

data class ContentFilter(val includedContentCategories: List<String>)

const val FAVORITES = "FAVORITES"
