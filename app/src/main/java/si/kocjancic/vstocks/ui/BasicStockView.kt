package si.kocjancic.vstocks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.GlideImage
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.models.ImageUrl
import si.kocjancic.vstocks.viewmodels.AllStocksViewModel

@Composable
fun BasicStockView(quote : Quotes,viewModel : AllStocksViewModel){
    var name by remember{ mutableStateOf(ImageUrl(quote.logoUrl))}
    val composableCouroutine = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
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
        Column(modifier=Modifier.padding(start=10.dp).weight(2f)){
            Text(text=quote.symbol)
            Text(text=quote.companyName ?: "",fontSize = 13.sp,overflow = TextOverflow.Ellipsis,maxLines = 1)
        }
        Text(text="%.2f€".format(quote.iexRealtimePrice),textAlign = TextAlign.Start,modifier = Modifier.padding(horizontal = 10.dp).weight(1f))
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