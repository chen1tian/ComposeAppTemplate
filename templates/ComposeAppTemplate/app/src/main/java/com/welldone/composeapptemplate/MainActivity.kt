package com.welldone.composeapptemplate

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
import com.welldone.composeapptemplate.ui.layout.MainLayout
import com.welldone.composeapptemplate.utils.theme.ComposeAppTemplateTheme
import com.welldone.composeapptemplate.viewmodels.mainlayout.MainLayoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainVm by viewModels<MainLayoutViewModel>()

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
    }
}
