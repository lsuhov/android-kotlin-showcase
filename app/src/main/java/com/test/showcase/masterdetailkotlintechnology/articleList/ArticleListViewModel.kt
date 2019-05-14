package com.test.showcase.masterdetailkotlintechnology.articleList

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.test.showcase.data.ArticleListService
import com.test.showcase.data.ArticlesRemoteDataSource
import com.test.showcase.data.model.ArticlePreviewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(private val articlesRemoteDataSource: ArticlesRemoteDataSource) : ViewModel() {

    val isLoading = ObservableBoolean(false)
    val filterZoneIsVisible = ObservableBoolean(false)
    val articleList = MutableLiveData<List<ArticlePreviewModel>>()
    val articleSections = ArticleListService.SECTIONS
    private val compositeDisposable = CompositeDisposable()

    var currentArticleSection: String = ArticleListService.DEFAULT_SECTION

    init {
        articleList.value = ArrayList()

        loadData(currentArticleSection)
    }

    private fun loadData(section: String = ArticleListService.DEFAULT_SECTION,
                         period: String = ArticleListService.DEFAULT_PERIOD) {
        isLoading.set(true)

        articlesRemoteDataSource.getListOfArticles(section, period)
                .subscribe(
                        { articles ->
                                articleList.value = articles
                                isLoading.set(false)
                        }
                        , {
                    isLoading.set(false)
                }).also { compositeDisposable.add(it) }
    }

    fun toggleFilterZoneVisibility() {
        filterZoneIsVisible.set(!filterZoneIsVisible.get())
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