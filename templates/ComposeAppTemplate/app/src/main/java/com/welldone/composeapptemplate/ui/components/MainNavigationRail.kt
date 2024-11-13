package com.welldone.composeapptemplate.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.welldone.composeapptemplate.ui.nav.NavigationItem

/**
 * 主导航栏
 */
@Composable
fun MainNavigationRail(
    modifier: Modifier = Modifier,
    selectedRoute: String,
    items: List<NavigationItem>,
    onClick: (NavigationItem) -> Unit,
) {
    NavigationRail(modifier = modifier) {
        items.forEachIndexed() { index, item ->
            NavigationRailItem(
                colors = NavigationRailItemColors(
                    selectedIconColor = Color(0xFFCDE1F3),
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    disabledIconColor = Color.LightGray,
                    disabledTextColor = Color.LightGray,
                    selectedIndicatorColor = Color(0xFF3975C6),
                ),
                icon = { Icon(painterResource(id = item.iconId), contentDescription = null) },
                label = { Text(text = stringResource(id = item.nameId)) },
                selected = item.route == selectedRoute,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainNavigationRailPreview() {
    MainNavigationRail(
        selectedRoute = NavigationItem.HOME.route,
        items = listOf(
            NavigationItem.HOME,
        ),
        onClick = {}
    )
}