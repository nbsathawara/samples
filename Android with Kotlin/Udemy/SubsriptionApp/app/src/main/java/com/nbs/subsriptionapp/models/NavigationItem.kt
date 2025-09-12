package com.nbs.subsriptionapp.models

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.nbs.subsriptionapp.data.Screens

sealed class NavigationItem(
    var title: String = "",
    var icon: ImageVector? = null,
    var painter: Painter? = null,
    var route: String = ""
) {
    data class DrawerItem(
        var id: Int,
        var dTitle: String = "",
        var dIcon: ImageVector? = null,
        var dPainter: Painter? = null,
        var dRoute: String = ""
    ) : NavigationItem(dTitle, dIcon, dPainter, dRoute)

    data class BottomItem(
        var id: Int,
        var bTitle: String = "",
        var bIcon: ImageVector? = null,
        var bPainter: Painter? = null,
        var bRoute: String = ""
    ) : NavigationItem(bTitle, bIcon, bPainter, bRoute)
}

val bottomItems = listOf<NavigationItem.BottomItem>(
    NavigationItem.BottomItem(id = 1, bRoute = Screens.HomeScreen.name),
    NavigationItem.BottomItem(id = 2, bRoute = Screens.BrowseScreen.name),
    NavigationItem.BottomItem(id = 3, bRoute = Screens.LibraryScreen.name)
)

val drawerItems = listOf<NavigationItem.DrawerItem>(
    NavigationItem.DrawerItem(id = 1, dRoute = Screens.MyAccountScreen.name),
    NavigationItem.DrawerItem(id = 2, dRoute = Screens.SubscriptionsScreen.name),
    NavigationItem.DrawerItem(id = 3, dRoute = Screens.AddAccountScreen.name)
)