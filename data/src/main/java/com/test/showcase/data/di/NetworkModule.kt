package com.test.showcase.data.di

import com.jcmsalves.codewarsapi.domain.RxSchedulers
import com.test.showcase.data.ArticleListService
import com.test.showcase.data.interceptor.AuthInterceptor
import com.test.showcase.data.BuildConfig
import com.test.showcase.data.RxSchedulersImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(AuthInterceptor())
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(ArticleListService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideArticleListService(retrofit: Retrofit) : ArticleListService {
        return retrofit.create(ArticleListService::class.java)
    }

    @Provides
    fun provideRxScheduler() : RxSchedulers = RxSchedulersImpl()
}