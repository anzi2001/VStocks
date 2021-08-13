package si.kocjancic.vstocks.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UrlCacheEntity(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val symbol : String,
    val url : String
)