package com.test.showcase.masterdetailkotlintechnology.articleList

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.test.showcase.data.ArticleListService
import com.test.showcase.data.ArticlesRemoteDataSource
import com.test.showcase.data.model.ArticlePreviewModel
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(private val articlesRemoteDataSource: ArticlesRemoteDataSource) : ViewModel() {

    val isLoading = ObservableBoolean(false)
    val filterZoneIsVisible = ObservableBoolean(false)
    val articleList = MutableLiveData<List<ArticlePreviewModel>>()
    val articleSections = ArticleListService.SECTIONS

    private var currentArticleSection: String = ArticleListService.DEFAULT_SECTION

    init {
        articleList.value = ArrayList()

        loadData(currentArticleSection)
    }

    private fun loadData(section: String = ArticleListService.DEFAULT_SECTION,
                         period: String = ArticleListService.DEFAULT_PERIOD) {
        isLoading.set(true)

        articlesRemoteDataSource.getListOfArticles(section, period)
                .doOnSuccess {
                    articleList.value = it
                    isLoading.set(false)
                }
                .doOnError{
                    isLoading.set(false)
                }
                .subscribe()
    }

    fun toggleFilterZoneVisibility() {
        filterZoneIsVisible.set(!filterZoneIsVisible.get())
    }

    fun setArticleSectionPosition(position: Int) {
        val newArticleSection = ArticleListService.SECTIONS[position]
        if (newArticleSection == currentArticleSection) {
            return
        }

        currentArticleSection = newArticleSection

        loadData(currentArticleSection)
    }

    override fun onCleared() {
        articleList.value = null

        super.onCleared()
    }
}