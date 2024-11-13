package com.welldone.composeapptemplate.utils

import android.content.Context
import android.content.pm.PackageInfo

class AppInfoUtils {
    companion object {
        fun getSelfAppInfo(context: Context): PackageInfo {
            val packageInfo: PackageInfo = context.getApplicationContext()
                .getPackageManager()
                .getPackageInfo(context.getPackageName(), 0);
            return packageInfo
        }
    }
}