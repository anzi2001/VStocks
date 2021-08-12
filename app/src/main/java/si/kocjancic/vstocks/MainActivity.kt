package si.kocjancic.vstocks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.ui.AddStock
import si.kocjancic.vstocks.ui.MainScreen
import si.kocjancic.vstocks.ui.SettingsScreen
import si.kocjancic.vstocks.ui.components.DetailedStockView
import si.kocjancic.vstocks.ui.theme.VStocksTheme
import si.kocjancic.vstocks.viewmodels.DetailedStockViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VStocksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    val mainNavigator = rememberNavController()
    Log.d("mymama","hshehef")
    NavHost(navController = mainNavigator, startDestination = "mainScreen"){
        composable("mainScreen"){
            MainScreen(mainNavigator)
        }
        composable("addStock"){ AddStock()}
        composable("settings"){ SettingsScreen()}
        composable("detailedStockView/{id}",arguments = listOf(navArgument("id"){type = NavType.StringType})){
            val symbol : String = it.arguments?.getString("id")!!
            val viewModel = hiltViewModel<DetailedStockViewModel>()
            DetailedStockView(symbol,viewModel)
        }
    }
}

@Preview(showBackground = true,showSystemUi = true, device = Devices.PIXEL_3)
@Composable
fun DefaultPreview() {
    VStocksTheme {
        Main()
    }
}