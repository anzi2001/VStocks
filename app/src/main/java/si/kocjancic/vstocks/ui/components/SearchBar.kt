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
@Composable
fun SearchBar(modifier: Modifier = Modifier){
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
        maxLines=1,
        placeholder = {Row{
            Icon(Icons.Default.Search, contentDescription = "test")
            Text("Search")
        }},
        onValueChange = { text = it}
    )
}