package test.scyscanner.com.scyscannertest.module.flights

import io.reactivex.Observable
import test.scyscanner.com.scyscannertest.application.api.ApiKeyProvider
import test.scyscanner.com.scyscannertest.application.api.SkyscannerApi
import test.scyscanner.com.scyscannertest.utils.SchedulersFactory

interface GetFlightsInteractor {

    fun getFlights(outboundDate: String, inboundDate: String): Observable<FlightsResultConverted>

}

class GetFlightsInteractorImpl(private val api: SkyscannerApi,
                               private val countryProvider: CountryProvider,
                               private val schedulersFactory: SchedulersFactory,
                               private val searchResultConverter: SearchResultConverter,
                               apiKeyProvider: ApiKeyProvider) : GetFlightsInteractor {

    private val apiKey = apiKeyProvider.provideApiKey()

    override fun getFlights(outboundDate: String, inboundDate: String): Observable<FlightsResultConverted> {
        val country = countryProvider.provideCountry()
        return api.createSession(
                country = country,
                outboundDate = outboundDate,
                inboundDate = inboundDate,
                apiKey = apiKey
        )
                .subscribeOn(schedulersFactory.io())
                .flatMap {
                    val header = it.response()!!.headers()
                    val url = header?.get("Location")
                            ?: throw IllegalArgumentException("No Location header")

                    api.pollResults(url).subscribeOn(schedulersFactory.io())
                }
                .map {
                    searchResultConverter.convertItems(it)
                }
    }

}