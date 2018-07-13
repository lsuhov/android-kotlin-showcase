package com.test.showcase.data;

import com.test.showcase.data.model.ArticlePreviewModel;
import com.test.showcase.data.model.ArticlesPreviewModel;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static ArticlesPreviewModel getArticlesPreviewModel() {

        return new ArticlesPreviewModel(4, getListOfArticlePreviewModel());
    }

    public static List<ArticlePreviewModel> getListOfArticlePreviewModel() {
        List<ArticlePreviewModel> articlePreviewModels = new ArrayList<>();
        articlePreviewModels.add(getArticlePreviewModel());
        articlePreviewModels.add(getArticlePreviewModel());
        articlePreviewModels.add(getArticlePreviewModel());

        return articlePreviewModels;
    }

    public static ArticlePreviewModel getArticlePreviewModel() {
        return new ArticlePreviewModel("https://www.nytimes.com/2018/07/08/health/world-health-breastfeeding-ecuador-trump.html",
                "Opposition to Breast-Feeding Resolution by U.S. Stuns World Health Officials",
                "2018-07-08",
                "By ANDREW JACOBS");
    }
}
