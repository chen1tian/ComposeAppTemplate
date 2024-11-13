package com.welldone.composeapptemplate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.welldone.composeapptemplate.database.testinfo.TestInfo
import com.welldone.composeapptemplate.database.testinfo.TestInfoDao
import java.io.File

@Database(
    entities = [TestInfo::class], version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testInfoDao(): TestInfoDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // todo: 根据需要修改数据库文件名
            val fileName = "sqlite.db"
            // 目录不存在则创建
            val dbFile = File(fileName)
            if (!dbFile.parentFile.exists()) {
                dbFile.parentFile.mkdirs()
            }

            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    fileName,
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}