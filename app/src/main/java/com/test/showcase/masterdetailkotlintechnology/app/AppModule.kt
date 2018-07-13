package com.test.showcase.masterdetailkotlintechnology.app

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: App): Context {
        return application.applicationContext
    }
}