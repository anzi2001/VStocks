package si.kocjancic.vstocks.models

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@JsonClass(generateAdapter=true)
@Parcelize
data class Quotes(
    val myStockAmount : Double?,
    @Transient
    var dataPulled : Boolean = false,
    val avgTotalVolume: Int?,
    val calculationPrice: String?,
    val change: Double?,
    val changePercent: Double?,
    val close: Int?,
    val closeSource: String?,
    val closeTime: String?,
    val companyName: String?,
    val delayedPrice: Int?,
    val delayedPriceTime: String?,
    val extendedChange: Int?,
    val extendedChangePercent: Double?,
    val extendedPrice: Int?,
    val extendedPriceTime: String?,
    val high: Double?,
    val highSource: String?,
    val highTime: Long?,
    val iexAskPrice: Double?,
    val iexAskSize: Int?,
    val iexBidPrice: Double?,
    val iexBidSize: Int?,
    val iexClose: Double?,
    val iexCloseTime: Long?,
    val iexLastUpdated: Long?,
    val iexMarketPercent: Double?,
    val iexOpen: Double?,
    val iexOpenTime: Long?,
    val iexRealtimePrice: Double?,
    val iexRealtimeSize: Int?,
    val iexVolume: Int?,
    val isUSMarketOpen: Boolean,
    val lastTradeTime: Long,
    val latestPrice: Double?,
    val latestSource: String,
    val latestTime: String,
    val latestUpdate: Long,
    val latestVolume: String?,
    val low: Double?,
    val lowSource: String?,
    val lowTime: Long?,
    val marketCap: Long?,
    val oddLotDelayedPrice: Double?,
    val oddLotDelayedPriceTime: String?,
    val `open`: Double?,
    val openSource: String,
    val openTime: String?,
    val peRatio: Double?,
    val previousClose: Double,
    val previousVolume: Int,
    val primaryExchange: String,
    val symbol: String,
    val volume: String?,
    val week52High: Double?,
    val week52Low: Double?,
    val ytdChange: Double?,
    @Transient
    var logoUrl : String = ""
) : Parcelable