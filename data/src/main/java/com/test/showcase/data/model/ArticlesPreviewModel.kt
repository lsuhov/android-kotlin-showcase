package com.test.showcase.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesPreviewModel (
    @SerializedName("num_results")
    val resultsNumber : Int,

    @SerializedName("results")
    val articles: List<ArticlePreviewModel>
)