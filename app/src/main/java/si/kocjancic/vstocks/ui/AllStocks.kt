package si.kocjancic.vstocks.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun AllStocks(){
    Text(text = "sup2")
}

@Preview(showBackground = true,showSystemUi = true,)
@Composable
fun AlLStocksPreview(){
    AllStocks()
}