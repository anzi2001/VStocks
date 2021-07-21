package si.kocjancic.vstocks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.models.ImageUrl
import si.kocjancic.vstocks.viewmodels.MyStocksViewModel

@Composable
fun MyStockView(quote : Quotes,viewModel : MyStocksViewModel){
    var name by remember{ mutableStateOf(ImageUrl(quote.logoUrl))}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement=Arrangement.SpaceBetween
    ){
        if(name.url.isEmpty()){
            LaunchedEffect(key1 = quote.symbol){
                val url = viewModel.pullSymbolImage(quote.symbol)
                quote.logoUrl = url.url
                name = url
            }
            Column(modifier= Modifier
                .width(50.dp)
                .height(50.dp)){}
        }
        else{
            Image(
                painter = rememberImagePainter(name.url, builder= {
                    crossfade(true)
                }),
                contentDescription = "hello world",
                modifier= Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape),
            )
        }
        Column(modifier=Modifier.padding(start=10.dp)){
            Text(text=quote.symbol)
            Text(text=quote.companyName ?: "")
        }

        Text(text=quote.iexRealtimePrice.toString(),modifier = Modifier.weight(1F),textAlign = TextAlign.End)
    }
}
