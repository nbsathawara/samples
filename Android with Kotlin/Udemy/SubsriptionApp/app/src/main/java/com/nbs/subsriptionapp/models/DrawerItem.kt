package com.nbs.subsriptionapp.models

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.nbs.subsriptionapp.data.Screens

data class DrawerItem(
    var id: Int,
    var title: String = "",
    var icon: ImageVector? = null,
    var painter: Painter? = null,
    var route: String = ""
)

val drawerItems = listOf<DrawerItem>(
    DrawerItem(id = 1, route = Screens.MyAccountScreen.name),
    DrawerItem(id = 2, route = Screens.SubscriptionsScreen.name),
    DrawerItem(id = 3, route = Screens.AddAccountScreen.name)
)