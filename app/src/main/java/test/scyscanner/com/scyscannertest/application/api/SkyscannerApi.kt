package test.scyscanner.com.scyscannertest.application.api

import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*
import test.scyscanner.com.scyscannertest.application.api.model.FlightsSearchResult

interface SkyscannerApi {

    @FormUrlEncoded
    @POST("/apiservices/pricing/v1.0/")
    fun createSession(@Field("country") country: String,
                      @Field("outboundDate") outboundDate: String,
                      @Field("inboundDate") inboundDate: String,
                      @Field("currency") currency: String = CURRENCY_USD,
                      @Field("locale") locale: String = LOCALE_US,
                      @Field("originPlace") originPlace: String = EDINGBURG_ID,
                      @Field("destinationPlace") destinationPlace: String = LONDON_ID,
                      @Field("adults") adults: Int = 1,
                      @Field("apiKey") apiKey: String): Observable<Result<Any>>

    @GET
    fun pollResults(@Url url: String) : Observable<FlightsSearchResult>


}

private const val EDINGBURG_ID = "EDIN-sky"
private const val LONDON_ID = "LOND-sky"
private const val LOCALE_US = "us-US"
private const val CURRENCY_USD = "USD"
private const val COUNTRY = "US"