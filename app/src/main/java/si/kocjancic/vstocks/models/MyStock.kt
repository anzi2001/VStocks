package si.kocjancic.vstocks.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyStock(
    val fullName : String,
    val symbol: String,
    val boughtAt: String,
    val shareNum: Double,
    val logoUrl: String?
) : Parcelable