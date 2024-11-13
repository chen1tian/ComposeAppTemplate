package com.welldone.composeapptemplate.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.welldone.composeapptemplate.ui.components.MainNavigationRail
import com.welldone.composeapptemplate.ui.nav.NavigationItem
import com.welldone.composeapptemplate.ui.nav.AppNavHost
import com.welldone.composeapptemplate.viewmodels.mainlayout.MainLayoutViewModel


// 菜单列表
val menuItems = listOf(
    NavigationItem.HOME,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    modifier: Modifier = Modifier,
    mainVm: MainLayoutViewModel
) {
    //
    val navController = rememberNavController()
    val state by mainVm.uiState.collectAsState()

    LaunchedEffect(key1 = state.curNavItem.route) {
        // 路由发生变化时，调整导航
        navController.navigate(state.curNavItem.route)
    }

    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // 导航栏
        MainNavigationRail(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface),
            selectedRoute = state.curNavItem.route,
            items = menuItems,
            onClick = { navItem ->
                mainVm.setCurNav(navItem)
            }
        )

        VerticalDivider()

        // 主界面
        Scaffold(
            modifier = Modifier.padding(4.dp),
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    navigationIcon = {
                        Icon(
                            painter = painterResource(state.curNavItem.iconId),
                            contentDescription = null
                        )
                    },
                    title = { Text(text = stringResource(id = state.curNavItem.nameId)) },
                    actions = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//                            Text(text = "V1.0.0")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding((innerPadding))
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceContainerLowest
            ) {
                AppNavHost(
                    navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 900, heightDp = 400)
@Composable
fun MainLaoutPreview() {
    MainLayout(
        mainVm = MainLayoutViewModel()
    )
}