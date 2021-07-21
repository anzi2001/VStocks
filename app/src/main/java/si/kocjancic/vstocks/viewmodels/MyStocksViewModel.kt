package si.kocjancic.vstocks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.api.IEXApi
import si.kocjancic.vstocks.api.pullMyStocks
import si.kocjancic.vstocks.models.ImageUrl
import si.kocjancic.vstocks.models.Quotes
import javax.inject.Inject

@HiltViewModel
class MyStocksViewModel @Inject constructor(private val iexApi: IEXApi) : ViewModel() {
    private val _myStocks = MutableLiveData<List<Quotes>>()
    val myStocks : LiveData<List<Quotes>> = _myStocks

    fun pullMyStocks(){
        viewModelScope.launch {
            _myStocks.value = Firebase.auth.currentUser?.pullMyStocks();
        }
    }

    suspend fun pullSymbolImage(symbol : String): ImageUrl {
        return iexApi.getLogoForSymbol(symbol)
    }
}