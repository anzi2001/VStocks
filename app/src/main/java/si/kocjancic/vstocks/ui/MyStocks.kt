package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import si.kocjancic.vstocks.viewmodels.MyStocksViewModel

@Composable
fun MyStocks(myStocksViewModel: MyStocksViewModel){
    val data by myStocksViewModel.myStocks.observeAsState()
    if (data == null){
        myStocksViewModel.pullMyStocks()
    }

    if(data == null){
        Column{

        }
    }
    else{
        Card(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            border = null
        ) {
            /*LazyColumn{
                items(data!!){ quote->
                    BasicStockView(quote = quote!!,viewModel = myStocksViewModel)
                    Divider()
                }
            }*/
        }

    }
}