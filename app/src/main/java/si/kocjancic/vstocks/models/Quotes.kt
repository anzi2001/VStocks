package si.kocjancic.vstocks.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter=true)
@Parcelize
@Entity
data class Quotes(
    var myStockAmount : Double? = null,
    @Transient
    var dataPulled : Boolean = false,
    var avgTotalVolume: Int? = null,
    var calculationPrice: String? = null,
    var change: Double? = null,
    var changePercent: Double? = null,
    var close: Double? = null,
    var closeSource: String? = null,
    var closeTime: String? = null,
    var companyName: String? = null,
    var delayedPrice: Double? = null,
    var delayedPriceTime: String? = null,
    var extendedChange: Double? = null,
    var extendedChangePercent: Double? = null,
    var extendedPrice: Double? = null,
    var extendedPriceTime: String? = null,
    var high: Double? = null,
    var highSource: String? = null,
    var highTime: Long? = null,
    var iexAskPrice: Double? = null,
    var iexAskSize: Int? = null,
    var iexBidPrice: Double? = null,
    var iexBidSize: Int? = null,
    var iexClose: Double? = null,
    var iexCloseTime: Long? = null,
    var iexLastUpdated: Long? = null,
    var iexMarketPercent: Double? = null,
    var iexOpen: Double? = null,
    var iexOpenTime: Long? = null,
    var iexRealtimePrice: Double? = null,
    var iexRealtimeSize: Int? = null,
    var iexVolume: Int? = null,
    var isUSMarketOpen: Boolean? = null,
    var lastTradeTime: Long? = null,
    var latestPrice: Double? = null,
    var latestSource: String? = null,
    var latestTime: String? = null,
    var latestUpdate: Long? = null,
    var latestVolume: String? = null,
    var low: Double? = null,
    var lowSource: String? = null,
    var lowTime: Long? = null,
    var marketCap: Long? = null,
    var oddLotDelayedPrice: Double? = null,
    var oddLotDelayedPriceTime: String? = null,
    var `open`: Double? = null,
    var openSource: String? = null,
    var openTime: String? = null,
    var peRatio: Double? = null,
    var previousClose: Double? = null,
    var previousVolume: Int? = null,
    var primaryExchange: String? = null,
    @PrimaryKey var symbol: String = "",
    var volume: String? = null,
    var week52High: Double? = null,
    var week52Low: Double? = null,
    var ytdChange: Double? = null,
    @Transient
    var logoUrl : String = ""
) : Parcelable