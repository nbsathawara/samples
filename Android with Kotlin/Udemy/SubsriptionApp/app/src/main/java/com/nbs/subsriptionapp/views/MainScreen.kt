package com.nbs.subsriptionapp.views

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.getValue
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.subsriptionapp.R
import com.nbs.subsriptionapp.custom.CustomSpacer
import com.nbs.subsriptionapp.custom.NavigationIcon
import com.nbs.subsriptionapp.data.Screens
import com.nbs.subsriptionapp.models.NavigationItem
import com.nbs.subsriptionapp.models.bottomItems
import com.nbs.subsriptionapp.models.drawerItems
import com.nbs.subsriptionapp.views.bottom.HomeScreen
import com.nbs.subsriptionapp.views.bottom.LibraryScreen
import com.nbs.subsriptionapp.views.drawer.AddAccountScreen
import com.nbs.subsriptionapp.views.drawer.MyAccountScreen
import com.nbs.subsriptionapp.views.drawer.SubscriptionsScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    drawerItems.forEach { drawerItem ->
        when (drawerItem.id) {
            1 -> {
                drawerItem.title = stringResource(R.string.my_account)
                drawerItem.icon = Icons.Default.AccountCircle
            }

            2 -> {
                drawerItem.title = stringResource(R.string.subscriptions)
                drawerItem.painter = painterResource(R.drawable.outline_subscriptions_24)
            }

            3 -> {
                drawerItem.title = stringResource(R.string.add_account)
                drawerItem.painter = painterResource(R.drawable.outline_person_add_24)
            }
        }
    }

    bottomItems.forEach { bottomItem ->
        when (bottomItem.id) {
            1 -> {
                bottomItem.title = stringResource(R.string.home)
                bottomItem.icon = Icons.Default.Home
            }

            2 -> {
                bottomItem.title = stringResource(R.string.browse)
                bottomItem.painter = painterResource(R.drawable.outline_browse_24)
            }

            3 -> {
                bottomItem.title = stringResource(R.string.genres)
                bottomItem.painter = painterResource(R.drawable.outline_library_music_24)
            }
        }
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var showBottomSheet by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    val primaryColor = MaterialTheme.colorScheme.primary
    val view = LocalView.current
    val isDarkTheme = isSystemInDarkTheme()
    LaunchedEffect(drawerState.isOpen) {
        val window = (view.context as? Activity)?.window ?: return@LaunchedEffect
        window.statusBarColor = primaryColor.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme

    }

    val scope = rememberCoroutineScope()
    fun closeDrawer() {
        scope.launch {
            drawerState.close()
        }
    }

    var currentNavigationItem by remember { mutableStateOf<NavigationItem>(drawerItems[0]) }
    var title by remember { mutableStateOf(currentNavigationItem.title) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent =
            {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                    ) {
                        DrawerHeaderView()
                        CustomSpacer(height = 2.dp)
                        drawerItems.forEach { drawerItem ->
                            val isSelected = drawerItem == currentNavigationItem
                            val color =
                                if (isSelected)
                                    MaterialTheme.colorScheme.secondary
                                else Color.White
                            val tintColor =
                                if (isSelected)
                                    MaterialTheme.colorScheme.onSecondary
                                else Color.Black
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color)
                                    .clickable {
                                        title = drawerItem.title
                                        currentNavigationItem = drawerItem
                                        navController.navigate(drawerItem.route)
                                        closeDrawer()
                                    }) {
                                Row(modifier = Modifier.padding(10.dp)) {
                                    if (drawerItem.icon != null) {
                                        Icon(
                                            imageVector = drawerItem.icon!!,
                                            contentDescription = "",
                                            tint = tintColor
                                        )
                                    } else {
                                        Icon(
                                            painter = drawerItem.painter!!,
                                            contentDescription = "",
                                            tint = tintColor
                                        )
                                    }
                                    CustomSpacer(width = 8.dp)
                                    Text(text = drawerItem.title, color = tintColor)
                                }
                            }
                            CustomSpacer(height = 4.dp)
                        }
                    }
                }
            }
    ) {

        Scaffold(
            topBar = {
                AppBar(
                    title = title,
                    navIcon = {
                        NavigationIcon(
                            icon = Icons.AutoMirrored.Filled.List,
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        )
                    },
                    actionIcons = {
                        IconButton({
                            showBottomSheet = true
                        }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
//                        when (currentNavigationItem) {
//                            drawerItems[0] -> {}
//                            drawerItems[1] -> {}
//                            drawerItems[2] -> {}
//                            else -> {}
//                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier
                        .navigationBarsPadding(),
                    containerColor = MaterialTheme.colorScheme.background,
                    tonalElevation = 2.dp
                ) {
                    bottomItems.forEachIndexed { index, bottomItem ->
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                                unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                            ),
                            selected = currentNavigationItem == bottomItem,
                            icon = {
                                if (bottomItem.icon != null)
                                    Icon(
                                        imageVector = bottomItem.icon!!,
                                        contentDescription = "",
                                    )
                                else
                                    Icon(
                                        painter = bottomItem.painter!!,
                                        contentDescription = "",
                                    )
                            },
                            label = {
                                Text(
                                    text = bottomItem.title,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            onClick = {
                                currentNavigationItem = bottomItem
                                title = bottomItem.title
                                navController.navigate(bottomItem.route)
                            }
                        )
                    }
                }
            }
        ) {
            Navigation(
                navController = navController,
                modifier = Modifier.padding(it)
            )

            if (showBottomSheet) {
                BottomSheet(
                    onDismiss = { showBottomSheet = false }
                )
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit = {}) {
    ModalBottomSheet(
        modifier = Modifier.navigationBarsPadding(),
        onDismissRequest = { onDismiss() },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            LazyColumn {
                items(5) { index ->
                    Text(
                        text = "Option ${index + 1}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun DrawerHeaderView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = "Menu",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.MyAccountScreen.name
    ) {
        composable(Screens.MyAccountScreen.name) {
            MyAccountScreen()
        }
        composable(Screens.SubscriptionsScreen.name) {
            SubscriptionsScreen()
        }
        composable(Screens.AddAccountScreen.name) {
            AddAccountScreen()
        }
        composable(Screens.HomeScreen.name) { it ->
            val isBrowse = it.arguments?.getString("isBrowse")
            HomeScreen()
        }
        composable(Screens.HomeScreen.name + "/{Browse}") { it ->
            HomeScreen(true)
        }
        composable(Screens.GenresScreen.name) {
            LibraryScreen()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MainScreen()
}