package si.kocjancic.vstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import si.kocjancic.vstocks.ui.AddStock
import si.kocjancic.vstocks.ui.MainScreen
import si.kocjancic.vstocks.ui.theme.VStocksTheme

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
    NavHost(navController = mainNavigator, startDestination = "mainScreen"){
        composable("mainScreen"){
            MainScreen(mainNavigator)
        }
        composable("addStock"){ AddStock()}
    }
}

@Preview(showBackground = true,showSystemUi = true, device = Devices.PIXEL_3)
@Composable
fun DefaultPreview() {
    VStocksTheme {
        Main()
    }
}