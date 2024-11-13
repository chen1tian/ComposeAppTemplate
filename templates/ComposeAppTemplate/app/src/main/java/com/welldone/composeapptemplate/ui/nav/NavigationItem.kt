package com.welldone.composeapptemplate.ui.nav

import com.welldone.composeapptemplate.R

sealed class NavigationItem(val route: String, val name: String, val nameId: Int, val iconId: Int) {
    /**
     * 主界面
     */
    object HOME : NavigationItem(Screen.HOME.name, "主页", R.string.nav_home, R.drawable.home)
}