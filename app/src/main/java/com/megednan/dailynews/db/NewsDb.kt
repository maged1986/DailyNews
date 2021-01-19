package com.megednan.dailynews.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.megednan.dailynews.models.Article
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(
        entities = [Article::class],
        version = 1
)
abstract class NewsDb : RoomDatabase() {
    abstract fun getArticleDao(): NewsDao
}