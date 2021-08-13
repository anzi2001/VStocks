package si.kocjancic.vstocks.ui

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.ui.components.BasicStockView
import si.kocjancic.vstocks.viewmodels.AllStocksViewModel

@Composable
fun AllStocks(allStocksViewModel : AllStocksViewModel, mainNavController : NavController){
    val data by allStocksViewModel.quoteData.observeAsState()
    val scope = rememberCoroutineScope()
    if (data == null){
        allStocksViewModel.pullLatestStocks()
        Column(
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            CircularProgressIndicator()
        }
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
                    val logoUrlState = MutableLiveData(quote!!.logoUrl)
                    if(logoUrlState.value!!.isEmpty()){
                        scope.launch(Dispatchers.IO) {
                            logoUrlState.postValue(allStocksViewModel.pullSymbolImage(quote.symbol))
                            quote.logoUrl = logoUrlState.value!!
                        }
                    }
                    BasicStockView(quote = quote,logoUrlState){
                        mainNavController.currentBackStackEntry?.arguments = Bundle().apply{putParcelable("detailedQuote",quote)}
                        mainNavController.navigate("detailedStockView/${quote.symbol}")
                    }
                    Divider()
                }
            }
        }

    }
}