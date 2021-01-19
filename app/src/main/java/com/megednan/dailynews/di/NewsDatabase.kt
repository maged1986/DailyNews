package com.megednan.dailynews.di

import android.app.Application
import androidx.room.Room
import com.megednan.dailynews.db.NewsDao
import com.megednan.dailynews.db.NewsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NewsDatabase{
    @Provides
    @Singleton
    fun provideDB(application: Application?) =
         Room.databaseBuilder(application!!, NewsDb::class.java, "articles")
                 .allowMainThreadQueries()
                .build()


    @Provides
    @Singleton
    fun provideYourDao(db: NewsDb) = db.getArticleDao() // The reason we can implement a Dao for the database

}