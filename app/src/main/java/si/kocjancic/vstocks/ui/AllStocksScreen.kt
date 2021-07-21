package si.kocjancic.vstocks.ui

import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import si.kocjancic.vstocks.ui.components.BasicStockView
import si.kocjancic.vstocks.viewmodels.AllStocksViewModel

@Composable
fun AllStocks(viewModel : AllStocksViewModel,navController : NavController){
    val data by viewModel.quoteData.observeAsState()
    if (data == null){
        viewModel.pullLatestStocks()
    }
    else{
        Card(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(horizontal=10.dp),
            shape = RoundedCornerShape(20.dp),
            border = null
        ) {
            LazyColumn{
                items(data!!){ quote->
                    BasicStockView(quote = quote!!,viewModel = viewModel){
                        navController.currentBackStackEntry?.arguments = Bundle().apply{putParcelable("detailedQuote",quote)}
                        navController.navigate("detailedStockView")
                    }
                    Divider()
                }
            }
        }

    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun AlLStocksPreview(){
    val mainViewModel = viewModel<AllStocksViewModel>()
    val navController = rememberNavController()
    AllStocks(mainViewModel,navController)
}