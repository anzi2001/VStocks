package si.kocjancic.vstocks.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputPopup(onCancelClick : ()->Unit,onConfirmClick:(String)->Unit){
    var text by remember{ mutableStateOf("")}
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
        Card(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            shape = RoundedCornerShape(20.dp),
            elevation=10.dp,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            border = null
        ){
            Column(modifier= Modifier
                .padding(10.dp)
                .fillMaxWidth()){
                Text("Number of fractions to buy",textAlign = TextAlign.Center)
                TextField(value = text,
                    label = {Text("Number of shares")},
                    onValueChange = {
                        text = it
                    },
                    colors= TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,

                )
                Spacer(modifier=Modifier.weight(1f))
                Row(horizontalArrangement = Arrangement.End,modifier=Modifier.fillMaxWidth()){
                    Text(text = "Cancel",modifier = Modifier
                        .clickable(onClick = onCancelClick)
                        .padding(10.dp))
                    Text(text="OK",modifier= Modifier
                        .clickable { onConfirmClick(text) }
                        .padding(10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun InputPopopPreview(){
    InputPopup(onCancelClick = {}, onConfirmClick = {})
}