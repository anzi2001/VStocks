package si.kocjancic.vstocks.room

import androidx.room.*
import si.kocjancic.vstocks.models.UrlCacheEntity

@Dao
interface UrlCacheDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity : UrlCacheEntity)

    @Delete
    suspend fun delete(entity: UrlCacheEntity)

    @Query("SELECT * FROM urlcacheentity WHERE symbol = :symbol LIMIT 1")
    suspend fun selectUrl(symbol : String): UrlCacheEntity?

    @Query("SELECT * FROM urlcacheentity")
    suspend fun selectAllUrls() : List<UrlCacheEntity>
}