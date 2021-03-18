package si.kocjancic.vstocks.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class ImageUrl(
    val url : String
)