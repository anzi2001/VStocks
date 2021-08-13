package si.kocjancic.vstocks.room

import androidx.room.Database
import androidx.room.RoomDatabase
import si.kocjancic.vstocks.models.Quotes
import si.kocjancic.vstocks.models.UrlCacheEntity

@Database(entities = [UrlCacheEntity::class,Quotes::class],version = 6)
abstract class AppDatabase : RoomDatabase(){
    abstract fun urlCacheDAO() : UrlCacheDAO
    abstract fun quoteCacheDao() : QuoteCacheDAO
}