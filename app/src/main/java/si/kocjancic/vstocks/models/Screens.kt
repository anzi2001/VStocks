package si.kocjancic.vstocks.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val iconVector: ImageVector,val label : String) {
    object MyStocks : Screens("MyStocks", Icons.Default.Leaderboard,"My stocks")
    object AllStocks : Screens("AllStocks", Icons.Default.ClearAll,"All stocks")
}