package com.test.showcase.masterdetailkotlintechnology.articleList

import com.test.showcase.data.model.ArticlePreviewModel

class ArticleListItemViewModel(articlePreviewModel: ArticlePreviewModel) {

    val title : String = articlePreviewModel.title
    val author : String = articlePreviewModel.author
    val date : String = articlePreviewModel.publishedDate
    val url: String = articlePreviewModel.url
}