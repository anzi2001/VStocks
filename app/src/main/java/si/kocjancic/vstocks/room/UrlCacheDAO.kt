package si.kocjancic.vstocks.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import si.kocjancic.vstocks.models.UrlCacheEntity

@Dao
interface UrlCacheDAO{
    @Insert
    suspend fun insert(entity : UrlCacheEntity)

    @Delete
    suspend fun delete(entity: UrlCacheEntity)

    @Query("SELECT * FROM urlcacheentity")
    suspend fun selectUrl(): UrlCacheEntity
}