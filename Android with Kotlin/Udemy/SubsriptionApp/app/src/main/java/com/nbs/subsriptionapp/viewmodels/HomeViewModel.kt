package com.nbs.subsriptionapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nbs.subsriptionapp.models.DrawerItem
import com.nbs.subsriptionapp.models.drawerItems

class HomeViewModel : ViewModel() {
    private var _curDrawerItem by mutableStateOf(drawerItems[0])
    var curDrawerItem: DrawerItem
        get() = _curDrawerItem
        set(value) {
            _curDrawerItem = value
        }
}