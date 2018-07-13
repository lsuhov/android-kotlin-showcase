package com.test.showcase.data;

import com.test.showcase.data.model.ArticlePreviewModel
import io.reactivex.Single
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(private var articleListService: ArticleListService,
                                                   private val rxSchedulers: RxSchedulers) {

    fun getListOfArticles(section : String = ArticleListService.DEFAULT_SECTION,
                          period : String = ArticleListService.DEFAULT_PERIOD) : Single<List<ArticlePreviewModel>> {

        return articleListService.getArticles(section, period)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.main())
                .map { it.articles }
    }
}
