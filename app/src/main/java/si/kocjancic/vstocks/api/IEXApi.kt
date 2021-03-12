package si.kocjancic.vstocks.api

import retrofit2.http.GET
import retrofit2.http.Path
import si.kocjancic.vstocks.models.ImageUrl
import si.kocjancic.vstocks.models.Quotes

interface IEXApi {
    @GET("stock/{stock}/quote?token=pk_c6b5fe8f166d404ab5eafe0565f3d788")
    suspend fun getSingleApi(@Path("stock") stock : String) : Quotes


    @GET("stock/{symbol}/logo?token=pk_c6b5fe8f166d404ab5eafe0565f3d788")
    suspend fun getLogoForSymbol(@Path("symbol") symbol : String) : ImageUrl

    @GET("stock/market/list/iexvolume?token=pk_c6b5fe8f166d404ab5eafe0565f3d788&listLimit=15")
    suspend fun getMostActiveList() : List<Quotes>
}