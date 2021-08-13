package si.kocjancic.vstocks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.kocjancic.vstocks.api.IEXApi
import si.kocjancic.vstocks.cache.QuoteCacheLayer
import si.kocjancic.vstocks.cache.UrlCacheLayer
import si.kocjancic.vstocks.models.Quotes
import javax.inject.Inject

@HiltViewModel
class AllStocksViewModel @Inject constructor(private val iexApi: IEXApi,private val quoteCacheLayer: QuoteCacheLayer,private val urlCacheLayer: UrlCacheLayer) : ViewModel(){
    private val quote : MutableLiveData<List<Quotes?>> = MutableLiveData(null)
    val quoteData : LiveData<List<Quotes?>> = quote
    fun pullLatestStocks() {
        viewModelScope.launch(Dispatchers.IO) {
            val mostActiveList = iexApi.getMostActiveList()
            quoteCacheLayer.storeQuotesInCache(mostActiveList)
            quote.postValue(mostActiveList)
        }
    }

    suspend fun pullSymbolImage(symbol : String): String {
        return urlCacheLayer.getUrl(symbol)
    }

}