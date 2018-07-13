package com.test.showcase.data;

import com.jcmsalves.codewarsapi.domain.TestRxSchedulersImpl;
import com.test.showcase.data.model.ArticlePreviewModel;
import com.test.showcase.data.model.ArticlesPreviewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticlesRemoteDataSourceUnitTest {

    ArticlesRemoteDataSource remoteDataSource;

    @Mock
    private ArticleListService mockArticleListService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        remoteDataSource = new ArticlesRemoteDataSource(mockArticleListService, new TestRxSchedulersImpl());
    }

    @Test
    public void getListOfArticleSuccess() {
        when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(getArticlesPreviewModel()));

        TestObserver testObserver = remoteDataSource
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
                .test();

        testObserver.assertNoErrors();
        testObserver.assertValue(new Predicate<List<ArticlePreviewModel>>() {
            @Override
            public boolean test(List<ArticlePreviewModel> list) throws Exception {
                return list.size() == 3;
            }
        });
        verify(mockArticleListService).getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD);
    }

    @Test
    public void getListOfArticlesWithError() {

        Throwable throwable = new Throwable();
        when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.<ArticlesPreviewModel>error(throwable));

        TestObserver testObserver = remoteDataSource
                .getListOfArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD)
                .test();

        testObserver.assertError(throwable);
        verify(mockArticleListService).getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD);
    }

    private ArticlesPreviewModel getArticlesPreviewModel() {
        List<ArticlePreviewModel> articlePreviewModels = new ArrayList<>();
        articlePreviewModels.add(getArticlePreviewModel());
        articlePreviewModels.add(getArticlePreviewModel());
        articlePreviewModels.add(getArticlePreviewModel());

        return new ArticlesPreviewModel(4, articlePreviewModels);
    }

    private ArticlePreviewModel getArticlePreviewModel() {
        return new ArticlePreviewModel("https://www.nytimes.com/2018/07/08/health/world-health-breastfeeding-ecuador-trump.html",
                "Opposition to Breast-Feeding Resolution by U.S. Stuns World Health Officials",
                "2018-07-08",
                "By ANDREW JACOBS");
    }
}