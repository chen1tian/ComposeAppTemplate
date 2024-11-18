package com.welldone.composeapptemplate.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

class StorageUtils {
    companion object {
        /**
         * 获取应用的根目录
         * @param context
         * @param appName 应用名称，也是创建的目录名
         * 需要在AndroidManifest.xml中添加权限
         * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
         * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
         */
        fun getAppRootPath(context: Context, appName: String): String {
            //判断外部存储器是否存在
            var sRootPath =
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                    (Environment.getExternalStorageDirectory().absolutePath + File.separator + appName)
                } else {
                    (context.filesDir.absolutePath + File.separator + appName)
                }
            return sRootPath
        }

        /**
         * 检查存储权限
         */
        fun checkStoragePermissions(context: Context?, activity: Activity?): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //23，android 6.0及以上版本
            {
                val permissions = arrayOf( //Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                if (
                    ContextCompat.checkSelfPermission(
                        context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                        context, Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // 权限还没有授予，进行申请
                    ActivityCompat.requestPermissions(
                        activity!!, permissions, 104
                    )
                    return false
                }
            }
            return true
        }
    }
}