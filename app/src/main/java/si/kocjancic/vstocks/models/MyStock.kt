package si.kocjancic.vstocks.models

data class MyStock(
    val fullName : String,
    val symbol: String,
    val boughtAt: String,
    val shareNum: Double,
    val logoUrl: String?
)