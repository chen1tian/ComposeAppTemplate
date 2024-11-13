package com.welldone.composeapptemplate.viewmodels.mainlayout

import androidx.lifecycle.ViewModel
import com.welldone.composeapptemplate.ui.nav.NavigationItem
import com.welldone.composeapptemplate.viewmodels.mainlayout.MainLayoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * 主界面ViewModel
 */
@HiltViewModel
class MainLayoutViewModel @Inject constructor() : ViewModel() {
    /**
     * UI状态
     */
    private var _uiState: MutableStateFlow<MainLayoutState> = MutableStateFlow(
        MainLayoutState(
            curNavItem = NavigationItem.HOME
        )
    )

    /**
     * UI状态
     */
    val uiState = _uiState.asStateFlow()

    /**
     * 设置当前导航项
     */
    fun setCurNav(navItem: NavigationItem) {
        _uiState.value = _uiState.value.copy(curNavItem = navItem)
    }
}