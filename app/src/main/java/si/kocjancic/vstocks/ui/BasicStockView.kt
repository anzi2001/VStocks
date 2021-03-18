package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.GlideImage
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.models.ImageUrl
import si.kocjancic.vstocks.viewmodels.MainViewModel

@Composable
fun BasicStockView(quote : Quotes,viewModel : MainViewModel){
    var name by remember{ mutableStateOf(ImageUrl(quote.logoUrl))}
    val composableCouroutine = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement=Arrangement.SpaceBetween
    ){
        if(name.url.isEmpty()){
            composableCouroutine.launch {
                val url = viewModel.pullSymbolImage(quote.symbol)
                quote.logoUrl = url.url
                name = url
            }
            Column(modifier= Modifier.width(50.dp).height(50.dp)){}
        }
        else{
            GlideImage(
                data = name.url,
                contentDescription = "hello",
                modifier=Modifier.width(50.dp).height(50.dp).clip(CircleShape),
                fadeIn = true,
            )
        }
        Text(text=quote.symbol,modifier= Modifier.padding(start=10.dp))
        Text(text=quote.iexRealtimePrice.toString(),modifier = Modifier.weight(1F),textAlign = TextAlign.End)
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