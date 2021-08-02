package si.kocjancic.vstocks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.compose.rememberImagePainter
import si.kocjancic.vstocks.models.Quotes

@Composable
fun BasicStockView(quote : Quotes, logoUrl: LiveData<String>, onClick: ()->Unit){
    val name by logoUrl.observeAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(20.dp)
    ){
        if(name!!.isEmpty()){
            Column(modifier= Modifier
                .width(50.dp)
                .height(50.dp)){}
        }
        else{
            quote.logoUrl = name!!
            Image(
                painter = rememberImagePainter(name,builder = {
                    crossfade(true)
                }),
                contentDescription = "hello",
                modifier= Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape),
            )
        }
        Column(modifier= Modifier
            .padding(start = 10.dp)
            .weight(2f)){
            Text(text=quote.symbol)
            Text(text=quote.companyName ?: "",fontSize = 13.sp,overflow = TextOverflow.Ellipsis,maxLines = 1)
        }
        Text(text="%.2f€".format(quote.iexRealtimePrice),textAlign = TextAlign.Start,modifier = Modifier
            .padding(horizontal = 10.dp)
            .weight(1f))
    }
}

@Preview
@Composable
fun BasicStockViewPreview(){
    //val quote = EasyRandom().nextObject(Quotes::class.java)
    //BasicStockView(quote, MutableLiveData("")){}
}