package si.kocjancic.vstocks.cache

import android.util.Log
import androidx.collection.LruCache
import kotlinx.coroutines.*
import si.kocjancic.vstocks.api.IEXApi
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.models.UrlCacheEntity
import si.kocjancic.vstocks.room.QuoteCacheDAO
import si.kocjancic.vstocks.room.UrlCacheDAO
import javax.inject.Inject


//Caching object for caching URL logos of companies
//strategy should be:
//request url -> is in memory? No-> is on disk? No-> get from network
//after fetching from the preferred medium, sync all
class UrlCacheLayer {
    @Inject
    lateinit var iexApi: IEXApi
    @Inject
    lateinit var urlCacheDAO: UrlCacheDAO
    private val urlCache = LruCache<String, String>(500)

    suspend fun getUrl(symbol : String) : String{
        var url = urlCache.get(symbol)
        if(url == null){
            url = getUrlFromDisk(symbol)
        }
        if(url == null){
            url = getUrlFromNetwork(symbol)
        }
        return url
    }

    private suspend fun getUrlFromNetwork(symbol : String) : String{
        //fetch url from network and update memory and disk
        val url = iexApi.getLogoForSymbol(symbol).url
        storeUrlOnDisk(symbol,url)
        urlCache.put(symbol,url)
        return url
    }
    private suspend fun storeUrlOnDisk(symbol : String,url : String){
        urlCacheDAO.insert(UrlCacheEntity(symbol = symbol,url = url))
    }
    private suspend fun getUrlFromDisk(symbol : String): String?{
        val url = urlCacheDAO.selectUrl(symbol)?.url
        url?.let {
            urlCache.put(symbol,it)
        }
        return url
    }
}

class QuoteCacheLayer @Inject constructor(private val iexApi: IEXApi, private val quoteCacheDAO: QuoteCacheDAO){
    private val quoteCache = LruCache<String, Quotes>(500)
    init{
        GlobalScope.launch(Dispatchers.IO) {
            quoteCacheDAO.selectAllQuotes().forEach{quoteCache.put(it.symbol,it)}
        }
    }

    suspend fun getQuote(symbol : String) : Quotes{
        var quote = quoteCache.get(symbol)
        if(quote == null){
            quote = getQuoteFromDisk(symbol)
        }
        if(quote == null){
            quote = getQuoteFromNetwork(symbol)
        }
        return quote
    }
    suspend fun storeQuotesInCache(quotes : List<Quotes>){
        quotes.forEach{quoteCache.put(it.symbol,it)}
        quoteCacheDAO.insert(*quotes.toTypedArray())
    }

    private suspend fun getQuoteFromNetwork(symbol : String) : Quotes{
        val quote = iexApi.getSingleQuote(symbol)
        storeQuoteOnDisk(quote)
        quoteCache.put(symbol,quote)
        return quote
    }

    private suspend fun storeQuoteOnDisk(quote : Quotes){
        quoteCacheDAO.insert(quote)
    }

    private suspend fun getQuoteFromDisk(symbol : String) : Quotes?{
        val quote = quoteCacheDAO.selectQuote(symbol)
        quote?.let {
            quoteCache.put(symbol,it)
        }
        return quote
    }
}