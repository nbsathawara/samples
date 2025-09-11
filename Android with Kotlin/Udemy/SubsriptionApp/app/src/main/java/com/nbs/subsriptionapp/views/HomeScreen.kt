package com.nbs.subsriptionapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.getValue
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.subsriptionapp.R
import com.nbs.subsriptionapp.custom.CustomSpacer
import com.nbs.subsriptionapp.custom.NavigationIcon
import com.nbs.subsriptionapp.data.Screens
import com.nbs.subsriptionapp.models.DrawerItem
import com.nbs.subsriptionapp.models.drawerItems
import com.nbs.subsriptionapp.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    drawerItems.forEach { drawerItem ->
        when (drawerItem.id) {
            1 -> {
                drawerItem.title = stringResource(R.string.my_account)
                drawerItem.icon = Icons.Default.AccountCircle
            }

            2 -> {
                drawerItem.title = stringResource(R.string.my_subscriptions)
                drawerItem.painter = painterResource(R.drawable.outline_subscriptions_24)
            }

            3 -> {
                drawerItem.title = stringResource(R.string.add_account)
                drawerItem.painter = painterResource(R.drawable.outline_person_add_24)
            }
        }
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()

    val scope = rememberCoroutineScope()
    fun closeDrawer() {
        scope.launch {
            drawerState.close()
        }
    }

    val viewModel: HomeViewModel = viewModel()
    var curDrawerItem by remember { mutableStateOf(viewModel.curDrawerItem) }
    var title by remember { mutableStateOf(curDrawerItem.title) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent =
            {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxWidth(0.65f),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                            .windowInsetsPadding(WindowInsets.statusBars)
                    ) {
                        DrawerHeaderView()
                        CustomSpacer(height = 2.dp)
                        drawerItems.forEach { drawerItem ->
                            val isSelected = drawerItem == curDrawerItem
                            val color =
                                if (isSelected)
                                    MaterialTheme.colorScheme.primary
                                else Color.White
                            val tintColor =
                                if (isSelected)
                                    MaterialTheme.colorScheme.onPrimary
                                else Color.Black
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color)
                                    .clickable {
                                        title = drawerItem.title
                                        curDrawerItem = drawerItem
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
                                    Text(text = drawerItem.title, color = tintColor)
                                }
                                CustomSpacer(height = 4.dp)
                            }
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

                    }
                )
            }
        ) {
            Navigation(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
fun DrawerItemView(
    drawerItem: DrawerItem,
    onItemClick: () -> Unit
) {
    NavigationDrawerItem(
        label = { Text(text = drawerItem.title) },
        icon = {
            if (drawerItem.icon != null) {
                Icon(
                    imageVector = drawerItem.icon!!,
                    contentDescription = ""
                )
            } else {
                Icon(
                    painter = drawerItem.painter!!,
                    contentDescription = ""
                )
            }
        },
        selected = false,
        onClick = {
            onItemClick()
        }
    )
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
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
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
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}