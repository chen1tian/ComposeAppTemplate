package com.welldone.composeapptemplate

import android.health.connect.datatypes.AppInfo
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.welldone.composeapptemplate.files.FileConfig
import com.welldone.composeapptemplate.logs.FileLoggingTree
import com.welldone.composeapptemplate.ui.layout.MainLayout
import com.welldone.composeapptemplate.ui.theme.ComposeAppTemplateTheme
import com.welldone.composeapptemplate.utils.AppInfoUtils
import com.welldone.composeapptemplate.viewmodels.mainlayout.MainLayoutViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 初始化文件目录, 设定外部存储目录和内部存储目录
        var appName = AppInfoUtils.getSelfAppInfo(this).applicationInfo.name
        FileConfig.init(this, appName)

        // 初始化主界面ViewModel
        val mainVm by viewModels<MainLayoutViewModel>()

        // 设置界面
        setContent {
            ComposeAppTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainLayout(
                        modifier = Modifier.padding(innerPadding),
                        mainVm = mainVm
                    )
                }
            }
        }

        // 进程间通信
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // 初始化日志
        initLog()
    }

    /**
     * 初始化日志
     */
    private fun initLog() {
        val logDir = File(FileConfig.extStorageDir + "/logs")
        Timber.plant(FileLoggingTree(logDir))
        Timber.plant(Timber.DebugTree())
    }
}
