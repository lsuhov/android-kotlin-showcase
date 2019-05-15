package com.test.showcase.masterdetailkotlintechnology.articleList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableBoolean
import com.test.showcase.data.ArticleListService
import com.test.showcase.data.ArticlesRemoteDataSource
import com.test.showcase.data.model.ArticlePreviewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(private val articlesRemoteDataSource: ArticlesRemoteDataSource) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val filterZoneIsVisible = MutableLiveData<Boolean>()
    val articleList = MutableLiveData<List<ArticlePreviewModel>>()
    val articleSections = ArticleListService.SECTIONS
    private val compositeDisposable = CompositeDisposable()

    var currentArticleSection: String = ArticleListService.DEFAULT_SECTION

    init {
        articleList.value = ArrayList()
        filterZoneIsVisible.value = false

        loadData(currentArticleSection)
    }

    private fun loadData(section: String = ArticleListService.DEFAULT_SECTION,
                         period: String = ArticleListService.DEFAULT_PERIOD) {
        isLoading.value = true

        articlesRemoteDataSource.getListOfArticles(section, period)
                .subscribe(
                        { articles ->
                                articleList.value = articles
                                isLoading.value = false
                        }
                        , {
                    isLoading.value = false
                }).also { compositeDisposable.add(it) }
    }

    fun toggleFilterZoneVisibility() {
        filterZoneIsVisible.value = filterZoneIsVisible.value?.not()
    }

    fun setArticleSectionPosition(position: Int) {
        if (position >= ArticleListService.SECTIONS.size) {
            return
        }

        val newArticleSection = ArticleListService.SECTIONS[position]
        if (newArticleSection == currentArticleSection) {
            return
        }

        currentArticleSection = newArticleSection

        loadData(currentArticleSection)
    }

    override fun onCleared() {
        articleList.value = null
        compositeDisposable.dispose()

        super.onCleared()
    }
}