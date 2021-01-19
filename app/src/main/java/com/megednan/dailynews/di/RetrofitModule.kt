package com.megednan.dailynews.di

import com.megednan.dailynews.network.NewsAPI
import com.megednan.dailynews.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
   /* val logging = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()*/
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
   // .client(client)
    .build()

    @Provides
    @Singleton
    fun provideNewsAPI(retrofit: Retrofit): NewsAPI =
            retrofit.create(NewsAPI::class.java)
}