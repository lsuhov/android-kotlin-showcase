package com.test.showcase.masterdetailkotlintechnology.articleList


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.showcase.data.ArticleListService
import com.test.showcase.data.ArticlesRemoteDataSource
import com.test.showcase.data.model.ArticlePreviewModel
import com.test.showcase.masterdetailkotlintechnology.ArticlePreviewModelStub
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockArticlesRemoteDataSource: ArticlesRemoteDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(mockArticlesRemoteDataSource.getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just<List<ArticlePreviewModel>>(ArticlePreviewModelStub.getListOfArticlePreviewModel()))
    }

    @Test
    fun loadData_Success() {
        val articleListViewModel = ArticleListViewModel(mockArticlesRemoteDataSource)

        Mockito.verify<ArticlesRemoteDataSource>(mockArticlesRemoteDataSource)
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
        assertFalse(articleListViewModel.isLoading.value!!)
    }

    @Test
    fun loadData_Fail() {
        Mockito.`when`(mockArticlesRemoteDataSource.getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.error(Throwable()))

        val articleListViewModel = ArticleListViewModel(mockArticlesRemoteDataSource)

        Mockito.verify<ArticlesRemoteDataSource>(mockArticlesRemoteDataSource)
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
        assertFalse(articleListViewModel.isLoading.value!!)
    }

    @Test
    fun toggleFilterZoneVisibility() {
        val articleListViewModel = ArticleListViewModel(mockArticlesRemoteDataSource)

        articleListViewModel.toggleFilterZoneVisibility()

        assertTrue(articleListViewModel.filterZoneIsVisible.value!!)
    }

    @Test
    fun setArticleSectionPosition_Valid() {
        val sectionPosition = 3
        val section = ArticleListService.SECTIONS[sectionPosition]
        Mockito.`when`<Single<List<ArticlePreviewModel>>>(mockArticlesRemoteDataSource.getListOfArticles(section, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just<List<ArticlePreviewModel>>(ArticlePreviewModelStub.getListOfArticlePreviewModel()))
        val articleListViewModel = ArticleListViewModel(mockArticlesRemoteDataSource)

        articleListViewModel.setArticleSectionPosition(sectionPosition)

        assertEquals(articleListViewModel.currentArticleSection, section)
    }

    @Test
    fun setArticleSectionPosition_Invalid() {
        val articleListViewModel = ArticleListViewModel(mockArticlesRemoteDataSource)

        articleListViewModel.setArticleSectionPosition(4)

        assertEquals(articleListViewModel.currentArticleSection, ArticleListService.DEFAULT_SECTION)
    }
}