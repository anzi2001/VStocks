package si.kocjancic.vstocks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.cache.QuoteCacheLayer
import si.kocjancic.vstocks.models.Quotes
import javax.inject.Inject

@HiltViewModel
class DetailedStockViewModel @Inject constructor(private val quoteCacheLayer: QuoteCacheLayer) : ViewModel(){
    private val _quote = MutableLiveData<Quotes?>(null)
    val quote : LiveData<Quotes?> = _quote

    fun getQuote(symbol : String){
        viewModelScope.launch {
            _quote.value = quoteCacheLayer.getQuote(symbol)
        }
    }
}