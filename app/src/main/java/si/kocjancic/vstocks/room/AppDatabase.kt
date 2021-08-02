package si.kocjancic.vstocks.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UrlCacheDAO::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun urlCacheDAO() : UrlCacheDAO
}