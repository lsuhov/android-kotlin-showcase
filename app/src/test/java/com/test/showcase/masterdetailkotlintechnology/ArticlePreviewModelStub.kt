package com.test.showcase.masterdetailkotlintechnology

import com.test.showcase.data.model.ArticlePreviewModel
import com.test.showcase.data.model.ArticlesPreviewModel
import java.util.*

class ArticlePreviewModelStub {
    companion object {
        fun getArticlesPreviewModel(): ArticlesPreviewModel {

            return ArticlesPreviewModel(4, getListOfArticlePreviewModel())
        }

        fun getListOfArticlePreviewModel() : List<ArticlePreviewModel> {
            val articlePreviewModels = ArrayList<ArticlePreviewModel>()
            articlePreviewModels.add(getArticlePreviewModel())
            articlePreviewModels.add(getArticlePreviewModel())
            articlePreviewModels.add(getArticlePreviewModel())

            return articlePreviewModels
        }

        fun getArticlePreviewModel() : ArticlePreviewModel {
            return ArticlePreviewModel ("https://www.nytimes.com/2018/07/08/health/world-health-breastfeeding-ecuador-trump.html",
                    "Opposition to Breast-Feeding Resolution by U.S. Stuns World Health Officials",
                    "2018-07-08",
                    "By ANDREW JACOBS")
        }
    }
}
