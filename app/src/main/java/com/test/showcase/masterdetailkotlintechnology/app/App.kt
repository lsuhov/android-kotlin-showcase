package com.test.showcase.masterdetailkotlintechnology.app

import com.test.showcase.masterdetailkotlintechnology.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}