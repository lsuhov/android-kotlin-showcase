package com.test.showcase.data

import com.test.showcase.data.model.ArticlesPreviewModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleListService {

    companion object {
        const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/"
        const val DEFAULT_SECTION = "all-sections"
        const val DEFAULT_PERIOD = "7"
        val SECTIONS = arrayListOf(DEFAULT_SECTION, "Arts", "Business Day", "Fashion & Style")
        val PERIODS = arrayListOf("1", DEFAULT_PERIOD, "30")
    }

    @GET("{section}/{period}.json")
    fun getArticles(@Path("section") section : String, @Path("period") period : String) :
            Single<ArticlesPreviewModel>
}