package com.welldone.composeapptemplate.files

import android.content.Context
import androidx.activity.ComponentActivity
import com.welldone.composeapptemplate.utils.StorageUtils

class FileConfig {
    companion object {
        private lateinit var _extStorageDir: String

        /**
         * 外部存储目录
         */
        val extStorageDir: String
            get() = _extStorageDir

        private lateinit var _filesDir: String

        /**
         * 内部存储目录
         */
        val filesDir: String
            get() = _filesDir

        fun init(activity: ComponentActivity, appName: String) {
            val context = activity.applicationContext

            // 检查并请求权限
            StorageUtils.checkStoragePermissions(context, activity)
            // 获取文件目录
            _extStorageDir = StorageUtils.getAppRootPath(context, appName)
            _filesDir = context.filesDir.absolutePath
            _extStorageDir = extStorageDir
            _filesDir = filesDir
        }
    }
}