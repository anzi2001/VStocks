package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.lazy.items
import si.kocjancic.vstocks.viewmodels.MainViewModel

@Composable
fun MyStocks(viewModel : MainViewModel){
    val data by viewModel.quoteData.observeAsState()
    if (data == null){
        viewModel.pullAapl()
    }

    if(data == null){
        Column{

        }
    }
    else{
        LazyColumn{
            items(data!!){ quote->
                BasicStockView(quote = quote!!,viewModel = viewModel)
            }
        }
    }

}