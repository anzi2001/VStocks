package si.kocjancic.vstocks.cache

import androidx.collection.LruCache


//Caching class for caching URL logos of companies
//strategy should be:
//request url -> is in memory? No-> is on disk? No-> get from network
//after fetching from the preferred medium, sync all
class LogoUrlCache {
    private val urlCache = LruCache<String, String>(500)
}