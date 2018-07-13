package com.test.showcase.masterdetailkotlintechnology.app

import com.test.showcase.data.di.NetworkModule
import com.test.showcase.masterdetailkotlintechnology.ActivitiesModule
import com.test.showcase.masterdetailkotlintechnology.util.di.ViewModelBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class,
            AppModule::class,
            ViewModelBuilder::class,
            ActivitiesModule::class,
            NetworkModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}