package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import si.kocjancic.vstocks.models.Screens
import si.kocjancic.vstocks.ui.components.SearchBar
import si.kocjancic.vstocks.viewmodels.AllStocksViewModel
import si.kocjancic.vstocks.viewmodels.MyStocksViewModel

@Composable
fun MainScreen(mainNavController: NavController){
    val screens = listOf(Screens.MyStocks, Screens.AllStocks)
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.secondary) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.iconVector,"test") },
                        label= { Text(text=screen.label) },
                        selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route){
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        },
        topBar={
            Row(verticalAlignment = Alignment.CenterVertically){
                SearchBar(modifier = Modifier
                    .weight(5f)
                    .padding(10.dp)
                ){

                }
                IconButton(
                    content={ Icon(Icons.Default.Settings,
                        contentDescription = "Settings icon",
                        modifier= Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(2.dp)
                    )},
                    onClick = {mainNavController.navigate("settings")},
                    modifier=Modifier.weight(1f),
                )
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
                    val myStocksViewModel: MyStocksViewModel = hiltViewModel(it)
                    MyStocks(myStocksViewModel)
                }
            }
            composable(Screens.AllStocks.route){
                Box(modifier = Modifier.padding(padding)){
                    val mainViewModel: AllStocksViewModel = hiltViewModel(it)
                    AllStocks(mainViewModel,mainNavController)
                }
            }
        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun MainScreenPreview(){
    val navController = rememberNavController()
    MainScreen(navController)
}