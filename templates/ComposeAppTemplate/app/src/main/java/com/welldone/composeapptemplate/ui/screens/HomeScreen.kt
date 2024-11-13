package com.welldone.composeapptemplate.ui.screens

import android.content.Context
import android.content.pm.PackageInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.welldone.composeapptemplate.R
import com.welldone.composeapptemplate.utils.AppInfoUtils


@Composable
fun HomeScreen() {
    Box(
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 主界面
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )

            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.surfaceContainerHighest
            )

            var appInfo = AppInfoUtils.getSelfAppInfo(LocalContext.current)
            Text(
                text = stringResource(id = R.string.version_txt, appInfo.versionName),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 920, heightDp = 640)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}