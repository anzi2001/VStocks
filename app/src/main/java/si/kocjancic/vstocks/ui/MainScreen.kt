package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import si.kocjancic.vstocks.models.Screens
import si.kocjancic.vstocks.viewmodels.MainViewModel

@Composable
fun MainScreen(mainNavController: NavController){
    val screens = listOf(Screens.MyStocks, Screens.AllStocks)
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.iconVector,"test") },
                        label= { Text(text=screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route){
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        })
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {mainNavController.navigate("addStock")}){
                Icon(Icons.Default.Add,"")
            }
        },

        ){ padding ->
        NavHost(navController,startDestination = Screens.MyStocks.route){
            composable(Screens.MyStocks.route){
                Box(modifier = Modifier.padding(padding)){
                    MyStocks()
                }

            }
            composable(Screens.AllStocks.route){
                Box(modifier = Modifier.padding(padding)){
                    val mainViewModel: MainViewModel = hiltNavGraphViewModel(it)
                    AllStocks(mainViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun MainScreenPrewiew(){
    val navController = rememberNavController()
    MainScreen(navController)
}