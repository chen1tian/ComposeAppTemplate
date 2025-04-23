package com.welldone.composeapptemplate.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
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
         * 根据不同Android版本申请相应的存储权限
         * - Android 6.0-9.0 (API 23-28): READ_EXTERNAL_STORAGE 和 WRITE_EXTERNAL_STORAGE
         * - Android 10 (API 29): 同上，但可以选择使用legacy storage选项
         * - Android 11+ (API 30+): 需要MANAGE_EXTERNAL_STORAGE权限获取完全访问权限
         * - Android 13+ (API 33+): 可以使用READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_AUDIO
         */
        fun checkStoragePermissions(context: Context?, activity: Activity?): Boolean {
            if (context == null || activity == null) return false

            // Android 13及以上版本 (API 33+)
            if (Build.VERSION.SDK_INT >= 33) { // Build.VERSION_CODES.TIRAMISU
                // 如果需要完全访问权限，使用MANAGE_EXTERNAL_STORAGE
                if (!Environment.isExternalStorageManager()) {
                    // 没有权限，跳转到设置页面申请MANAGE_EXTERNAL_STORAGE权限
                    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                    val uri = Uri.fromParts("package", activity.packageName, null)
                    intent.data = uri
                    activity.startActivity(intent)
                    return false
                }
                return true

                // 如果只需要访问媒体文件，可以使用以下代码替代上面的代码
                /*
                val permissions = arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_AUDIO
                )

                val needRequestPermission = permissions.any { permission ->
                    ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
                }

                if (needRequestPermission) {
                    ActivityCompat.requestPermissions(activity, permissions, 104)
                    return false
                }
                return true
                */
            }
            // Android 11-12 (API 30-32)
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // 检查是否有MANAGE_EXTERNAL_STORAGE权限
                if (!Environment.isExternalStorageManager()) {
                    // 没有权限，跳转到设置页面申请MANAGE_EXTERNAL_STORAGE权限
                    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                    val uri = Uri.fromParts("package", activity.packageName, null)
                    intent.data = uri
                    activity.startActivity(intent)
                    return false
                }
                return true
            }
            // Android 10 (API 29)
            else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                val permissions = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 权限还没有授予，进行申请
                    ActivityCompat.requestPermissions(activity, permissions, 104)
                    return false
                }
                return true
            }
            // Android 6.0-9.0 (API 23-28)
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val permissions = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 权限还没有授予，进行申请
                    ActivityCompat.requestPermissions(activity, permissions, 104)
                    return false
                }
                return true
            }

            // Android 6.0以下版本不需要动态申请权限
            return true
        }
    }
}