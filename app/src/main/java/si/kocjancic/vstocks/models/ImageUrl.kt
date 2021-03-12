package si.kocjancic.vstocks.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUrl(
    val url : String
) : Parcelable