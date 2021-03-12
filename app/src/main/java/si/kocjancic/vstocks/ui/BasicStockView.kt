package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.glide.GlideImage
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.models.ImageUrl
import si.kocjancic.vstocks.viewmodels.MainViewModel

@Composable
fun BasicStockView(quote : Quotes,viewModel : MainViewModel){
    var name by remember{ mutableStateOf(ImageUrl(""))}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement=Arrangement.SpaceBetween
    ){
        val composableCouroutine = rememberCoroutineScope()
        if(name.url.isEmpty()){
            composableCouroutine.launch { name = viewModel.pullSymbolImage(quote.symbol) }
            Text("loading")
        }
        else{
            GlideImage(data = name.url,contentDescription = "hello",modifier=Modifier.width(50.dp))
        }
        Text(text="${quote.companyName} (${quote.symbol})")
        Text(text=quote.iexRealtimePrice.toString())
    }
}

@Preview
@Composable
fun BasicStockViewPreview(){
    /*val quote = Quotes(
        companyName = "TEST",
        symbol = "TSTT",
        iexRealtimePrice = 5.0

    )
    BasicStockView(quote)*/
}