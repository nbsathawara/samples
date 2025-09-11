//package com.nbs.subsriptionapp.views
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.statusBars
//import androidx.compose.foundation.layout.windowInsetsPadding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalDrawerSheet
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.NavigationDrawerItem
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.rememberNavController
//import com.nbs.subsriptionapp.R
//import com.nbs.subsriptionapp.data.Screens
//import com.nbs.subsriptionapp.models.DrawerItem
//import com.nbs.subsriptionapp.models.drawerItems
//import com.nbs.subsriptionapp.viewmodels.HomeViewModel
//import kotlinx.coroutines.launch
//
//@Composable
//fun NavigationDrawer() {
//
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val navController = rememberNavController()
//
//    drawerItems.addAll(
//        listOf(
//            DrawerItem(
//                title = stringResource(R.string.my_account),
//                icon = Icons.Default.AccountCircle,
//                onItemSelect = {
//                    homeViewModel.curDrawerItem = drawerItems[0]
//                    navController.navigate(Screens.MyAccountScreen.name)
//                    closeDrawer()
//                }
//            ),
//            DrawerItem(
//                title = stringResource(R.string.my_subscriptions),
//                painter = painterResource(R.drawable.outline_subscriptions_24),
//                onItemSelect = {
//                    homeViewModel.curDrawerItem = drawerItems[1]
//                    navController.navigate(Screens.SubscriptionsScreen.name)
//                    closeDrawer()
//                }
//            ),
//            DrawerItem(
//                title = stringResource(R.string.add_account),
//                painter = painterResource(R.drawable.outline_person_add_24),
//                onItemSelect = {
//                    homeViewModel.curDrawerItem = drawerItems[2]
//                    closeDrawer()
//                }
//            ))
//    )
//
//    homeViewModel = viewModel()
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent =
//            {
//                ModalDrawerSheet(
//                    modifier = Modifier
//                        .fillMaxWidth(0.75f),
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .windowInsetsPadding(WindowInsets.statusBars)
//                    ) {
//                        DrawerHeaderView()
//                        drawerItems.forEach { drawerItem ->
//                            DrawerItemView(drawerItem)
//                        }
//                    }
//                }
//            }
//    ) {
//        HomeScreen(drawerState, navController)
//    }
//}
//
//@Composable
//fun DrawerItemView(drawerItem: DrawerItem) {
//    return NavigationDrawerItem(
//        label = { Text(text = drawerItem.title) },
//        icon = {
//            if (drawerItem.icon != null) {
//                Icon(
//                    imageVector = drawerItem.icon!!,
//                    contentDescription = ""
//                )
//            } else {
//                Icon(
//                    painter = drawerItem.painter!!,
//                    contentDescription = ""
//                )
//            }
//        },
//        selected = drawerItem.isSelected,
//        onClick = {
//            drawerItem.onItemSelect()
//        }
//    )
//}
//
//@Composable
//fun DrawerHeaderView() {
//    NavigationDrawerItem(
//        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
//        label = {
//            Text(
//                text = "Menu",
//                color = MaterialTheme.colorScheme.onPrimary
//            )
//        },
//        selected = false,
//        onClick = {
//        }
//    )
//}
//
//
////@Composable
////fun Navigation(
////    drawerState: DrawerState,
////    navController: NavHostController,
////    homeViewModel: HomeViewModel = viewModel(),
////) {
////    NavHost(
////        navController = navController,
////        startDestination = Screens.HomeScreen.name
////    ) {
////        composable(Screens.HomeScreen.name) {
////            HomeScreen(drawerState, navController, homeViewModel)
////        }
////        composable(Screens.MyAccountScreen.name) {
////            MyAccountScreen()
////        }
////        composable(Screens.SubscriptionsScreen.name) {
////            SubscriptionsScreen()
////        }
////    }
////}
//
//
