package si.kocjancic.vstocks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.api.IEXApi
import si.kocjancic.vstocks.models.ImageUrl
import si.kocjancic.vstocks.models.Quotes
import javax.inject.Inject

@HiltViewModel
class AllStocksViewModel @Inject constructor(private val iexApi: IEXApi) : ViewModel(){
    private val quote : MutableLiveData<List<Quotes?>> = MutableLiveData(null)
    val quoteData : LiveData<List<Quotes?>> = quote
    fun pullAapl() {
        viewModelScope.launch {
            quote.value = iexApi.getMostActiveList()
        }
    }

    suspend fun pullSymbolImage(symbol : String): ImageUrl {
        return iexApi!!.getLogoForSymbol(symbol)
    }


}