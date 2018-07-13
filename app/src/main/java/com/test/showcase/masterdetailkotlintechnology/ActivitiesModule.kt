package com.test.showcase.masterdetailkotlintechnology

import android.arch.lifecycle.ViewModel
import com.test.showcase.masterdetailkotlintechnology.articleList.ArticleListViewModel
import com.test.showcase.masterdetailkotlintechnology.articleList.ArticlesListActivity
import com.test.showcase.masterdetailkotlintechnology.util.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ActivitiesModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): ArticlesListActivity

    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    abstract fun bindMainViewModel(viewModel: ArticleListViewModel): ViewModel


}