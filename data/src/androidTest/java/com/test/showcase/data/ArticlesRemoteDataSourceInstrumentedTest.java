package com.test.showcase.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.test.showcase.data.di.NetworkModule;
import com.test.showcase.data.model.ArticlePreviewModel;
import com.test.showcase.data.rx.RxSchedulersImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ArticlesRemoteDataSourceInstrumentedTest {

    private ArticlesRemoteDataSource remoteDataSource;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.test.showcase.data.test", appContext.getPackageName());
    }

    @Before
    public void setup() {

        NetworkModule networkModule = new NetworkModule();
        OkHttpClient okHttpClient = networkModule.provideHttpClient();
        Retrofit retrofit = networkModule.provideRetrofit(okHttpClient);
        ArticleListService articleListService = networkModule.provideArticleListService(retrofit);

        remoteDataSource = new ArticlesRemoteDataSource(articleListService, new RxSchedulersImpl());
    }

    @Test
    public void articleRemoteDataSourceReturnsDataForValidParameters() {
        List<ArticlePreviewModel> list = remoteDataSource
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
                .blockingGet();

        assertTrue(list.size() > 0);
    }

    @Test
    public void articleRemoteDataSourceDoesntReturnDataForInvalidSection() {
        List<ArticlePreviewModel> list = remoteDataSource
                .getListOfArticles("test", ArticleListService.DEFAULT_PERIOD)
                .blockingGet();

        assertTrue(list.size() == 0);
    }

    @Test
    public void articleRemoteDataSourceDoesntReturnDataForInvalidPeriod() {

        try {
            List<ArticlePreviewModel> list = remoteDataSource
                    .getListOfArticles(ArticleListService.DEFAULT_SECTION, "test")
                    .blockingGet();

            assertTrue(list.size() == 0);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
