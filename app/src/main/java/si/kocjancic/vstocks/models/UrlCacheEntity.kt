package si.kocjancic.vstocks.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UrlCacheEntity(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    @ColumnInfo(name="symbol") val symbol : String?,
    @ColumnInfo(name="url") val url : String?
)