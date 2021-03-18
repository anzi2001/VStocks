package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.Column
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
import si.kocjancic.vstocks.viewmodels.MainViewModel

@Composable
fun AllStocks(viewModel : MainViewModel){
    val data by viewModel.quoteData.observeAsState()
    if (data == null){
        viewModel.pullAapl()
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
            LazyColumn{
                items(data!!){ quote->
                    BasicStockView(quote = quote!!,viewModel = viewModel)
                    Divider()
                }
            }
        }

    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun AlLStocksPreview(){
    val mainViewModel = viewModel<MainViewModel>()
    AllStocks(mainViewModel)
}