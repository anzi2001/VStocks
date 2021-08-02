package si.kocjancic.vstocks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import si.kocjancic.vstocks.models.Quotes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun DetailedStockView(quote : Quotes){
    val color = remember{if(quote.change!! < 0) Color.Red else Color.Green}
    var expanded by remember { mutableStateOf(false)}
    Scaffold{
        Column{
            Column(modifier= Modifier
                .padding(it)
                .weight(19f)
                .verticalScroll(rememberScrollState())){
                Card(
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = null
                ) {
                    Column{
                        Row(verticalAlignment = Alignment.CenterVertically,modifier=Modifier.padding(10.dp)){
                            Text(quote.symbol,fontSize = 30.sp,modifier=Modifier.padding(end=10.dp))
                            Image(
                                painter = rememberImagePainter(quote.logoUrl),
                                contentDescription="logo image of company",
                                modifier= Modifier
                                    .width(50.dp)
                                    .height(50.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text=quote.iexRealtimePrice.toString(),fontSize = 20.sp)
                            Text(text=quote.change.toString(),fontSize = 13.sp,modifier=Modifier.padding(bottom=18.dp),color = color)
                        }
                    }
                }
                Card(
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = null
                ) {
                    Column{
                        Row(verticalAlignment = Alignment.CenterVertically,modifier=Modifier.padding(10.dp)){
                            Text("Open: ",fontWeight = FontWeight.Bold)
                            Text(quote.iexOpen.toString(),fontSize = 15.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("Close: ",fontWeight = FontWeight.Bold)
                            Text(quote.iexClose.toString(),fontSize = 15.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically,modifier=Modifier.padding(10.dp)){
                            Text("Week52High: ",fontWeight = FontWeight.Bold)
                            Text(quote.week52High.toString(),fontSize = 15.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("Week52Low:",fontWeight = FontWeight.Bold)
                            Text(quote.week52Low.toString(),fontSize = 15.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically,modifier=Modifier.padding(10.dp)){
                            Text("Market cap: ",fontWeight = FontWeight.Bold)
                            Text(quote.marketCap.toString(),fontSize = 15.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("Volume: ",fontWeight = FontWeight.Bold)
                            Text(quote.previousVolume.toString(),fontSize = 15.sp)
                        }
                    }
                }
                Card(
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = null
                ) {
                    //we currently do not have a charting library that is up to date so this is a TODO
                    //insert graph here
                }
            }
            Column(modifier=Modifier.weight(1f)){
                Button(onClick = {
                    expanded = true
                },  modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                    border = null,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                ) {
                    Text("Add to my stocks")
                }
            }
        }
    }
    if(expanded){
        InputPopup(
            onCancelClick = {
                expanded = false
            }, onConfirmClick = {

            }
        )
    }

}