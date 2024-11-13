package com.welldone.composeapptemplate.database

import android.content.Context
import com.welldone.composeapptemplate.database.testinfo.TestInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 数据操作依赖注入模块
 */
class DatabaseDIModule {
    // Room数据库 依赖注入
    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
            return AppDatabase.getDatabase(context)
        }

        @Provides
        @Singleton
        fun provideTestInfoDao(database: AppDatabase): TestInfoDao {
            return database.testInfoDao()
        }
    }
}