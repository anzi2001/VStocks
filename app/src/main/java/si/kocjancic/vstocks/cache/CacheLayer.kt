package si.kocjancic.vstocks.cache

import androidx.collection.LruCache
import si.kocjancic.vstocks.api.IEXApi
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.models.UrlCacheEntity
import si.kocjancic.vstocks.room.UrlCacheDAO
import javax.inject.Inject


//Caching object for caching URL logos of companies
//strategy should be:
//request url -> is in memory? No-> is on disk? No-> get from network
//after fetching from the preferred medium, sync all
object CacheLayer {
    @Inject
    lateinit var iexApi: IEXApi
    @Inject
    lateinit var urlCacheDAO: UrlCacheDAO
    private val urlCache = LruCache<String, String>(500)
    private val quoteCache = LruCache<String, Quotes>(500)

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

    suspend fun getQuote(symbol : String) : Quotes{
        var quote = quoteCache.get(symbol)
        if(quote == null){
            //pull from disk
        }
        if(quote == null){
            //pull from network
        }
        return quote
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
        val url = urlCacheDAO.selectUrl().url
        url?.let {
            urlCache.put(symbol,it)
        }
        return url
    }
}