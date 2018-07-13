package com.test.showcase.data.model;

import com.google.gson.annotations.SerializedName

data class ArticlePreviewModel (

    @SerializedName("url")
    val url : String,

    @SerializedName("title")
    val title : String,

    @SerializedName("published_date")
    val publishedDate : String,

    @SerializedName("byline")
    val author : String
)