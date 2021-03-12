package si.kocjancic.vstocks.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, @StringRes val resourceId: Int, val iconVector: ImageVector,val label : String) {
    object MyStocks : Screens("MyStocks",14, Icons.Default.Leaderboard,"My stocks")
    object AllStocks : Screens("AllStocks",15, Icons.Default.ClearAll,"All stocks")
}