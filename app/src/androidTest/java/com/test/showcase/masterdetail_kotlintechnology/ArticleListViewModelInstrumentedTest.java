package com.test.showcase.masterdetail_kotlintechnology;

import android.app.Instrumentation;
import android.support.test.runner.AndroidJUnit4;

import com.test.showcase.data.ArticleListService;
import com.test.showcase.data.ArticlesRemoteDataSource;
import com.test.showcase.data.rx.RxSchedulersImpl;
import com.test.showcase.masterdetailkotlintechnology.articleList.ArticleListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Single;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ArticleListViewModelInstrumentedTest {

    @Mock
    private ArticleListService mockArticleListService;

    private ArticlesRemoteDataSource mockArticlesRemoteDataSource;
    private Instrumentation instrumentation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockArticlesRemoteDataSource = new ArticlesRemoteDataSource(mockArticleListService, new RxSchedulersImpl());
        instrumentation = getInstrumentation();
    }

    @Test
    public void initialLoadIsSetAsExpected() {
        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.Companion.getArticlesPreviewModel()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();

        instrumentation.runOnMainSync(() ->
                viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource)));
        instrumentation.waitForIdleSync();


        Mockito.verify(mockArticleListService).getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD);

        assertFalse(viewModel.get().isLoading().getValue());

        assertEquals("size of articles list is different", viewModel.get().getArticleList().getValue().size(), TestData.Companion.getListOfArticlePreviewModel().size());
    }

    @Test
    public void loadDataException() {
        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.error(new Throwable()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();

        instrumentation.runOnMainSync(() ->
                viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource)));
        instrumentation.waitForIdleSync();

        Mockito.verify(mockArticleListService).getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD);
        assertFalse(viewModel.get().isLoading().getValue());
    }

    @Test
    public void toggleFilterArea() {
        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.Companion.getArticlesPreviewModel()));
        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();

        instrumentation.runOnMainSync(() -> {
            viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource));

            viewModel.get().toggleFilterZoneVisibility();
        });
        instrumentation.waitForIdleSync();

        assertTrue(viewModel.get().getFilterZoneIsVisible().getValue());
    }

    @Test
    public void setInvalidSectionPosition() {
        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.Companion.getArticlesPreviewModel()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();

        instrumentation.runOnMainSync(() -> {
            viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource));

            viewModel.get().setArticleSectionPosition(4);
        });
        instrumentation.waitForIdleSync();


        assertEquals(viewModel.get().getCurrentArticleSection(), ArticleListService.DEFAULT_SECTION);
    }

    @Test
    public void setValidSectionPosition() {
        int sectionPosition = 3;
        String section = ArticleListService.Companion.getSECTIONS().get(sectionPosition);

        Mockito.when(mockArticleListService.getArticles(section, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.Companion.getArticlesPreviewModel()));

        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.Companion.getArticlesPreviewModel()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();

        instrumentation.runOnMainSync(() -> {
            viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource));

            viewModel.get().setArticleSectionPosition(sectionPosition);
        });
        instrumentation.waitForIdleSync();


        assertEquals(viewModel.get().getCurrentArticleSection(), section);
    }
}
