package si.kocjancic.vstocks.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(modifier: Modifier = Modifier,onValueChange : (String) -> Unit){
    var text by remember{ mutableStateOf("")}
    TextField(text,
        colors= TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.secondary
        ),
        modifier= modifier,
        shape= RoundedCornerShape(50),
        singleLine=true,
        placeholder = {Row{
            Icon(Icons.Default.Search, contentDescription = "test")
            Text("Search")
        }},
        onValueChange = {
            text = it
            onValueChange(it)
        }
    )
}