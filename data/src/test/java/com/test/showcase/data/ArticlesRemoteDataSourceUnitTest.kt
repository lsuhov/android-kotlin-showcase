package com.test.showcase.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.showcase.data.model.ArticlesPreviewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner::class)
class ArticlesRemoteDataSourceUnitTest {

    private val mockArticleListService = mock<ArticleListService>()
    private lateinit var remoteDataSource: ArticlesRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = ArticlesRemoteDataSource(mockArticleListService, TestRxSchedulersImpl())
    }

    @Test
    fun getListOfArticleSuccess() {
        whenever(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.getArticlesPreviewModel()))

        val testObserver = remoteDataSource
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
                .test()

        testObserver.assertNoErrors()
        testObserver.assertValue {
            it.size == 3
        }
        verify(mockArticleListService).getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
    }

    @Test
    fun getListOfArticlesWithError() {

        val throwable = Throwable()
        whenever(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.error<ArticlesPreviewModel>(throwable))

        val testObserver = remoteDataSource
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
                .test()

        testObserver.assertError(throwable)
        verify(mockArticleListService).getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
    }

}