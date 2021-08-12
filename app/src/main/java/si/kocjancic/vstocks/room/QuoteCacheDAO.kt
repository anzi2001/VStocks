package si.kocjancic.vstocks.room

import androidx.room.*
import si.kocjancic.vstocks.models.Quotes

@Dao
interface QuoteCacheDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg quote: Quotes)

    @Query("SELECT * FROM quotes WHERE symbol = :symbol LIMIT 1")
    suspend fun selectQuote(symbol : String): Quotes?

    @Query("SELECT * FROM quotes")
    suspend fun selectAllQuotes(): List<Quotes>

    @Delete
    suspend fun delete(quote: Quotes)
}