package com.test.showcase.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.showcase.data.di.NetworkModule
import com.test.showcase.data.rx.RxSchedulersImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ArticlesRemoteDataSourceInstrumentedTest {

    private var remoteDataSource: ArticlesRemoteDataSource? = null

    @Before
    fun setup() {

        val networkModule = NetworkModule()
        val okHttpClient = networkModule.provideHttpClient()
        val retrofit = networkModule.provideRetrofit(okHttpClient)
        val articleListService = networkModule.provideArticleListService(retrofit)

        remoteDataSource = ArticlesRemoteDataSource(articleListService, RxSchedulersImpl())
    }

    @Test
    fun articleRemoteDataSourceReturnsDataForValidParameters() {
        val list = remoteDataSource!!
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
                .blockingGet()

        assertTrue(list.isNotEmpty())
    }

    @Test
    fun articleRemoteDataSourceDoesntReturnDataForInvalidSection() {
        val list = remoteDataSource!!
                .getListOfArticles("test", ArticleListService.DEFAULT_PERIOD)
                .blockingGet()

        assertEquals(0, list.size.toLong())
    }

    @Test(expected = HttpException::class)
    fun articleRemoteDataSourceDoesntReturnDataForInvalidPeriod() {

        remoteDataSource!!
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, "test")
                .blockingGet()
    }
}
