package test.scyscanner.com.scyscannertest.module.flights

import test.scyscanner.com.scyscannertest.application.api.ApiKeyProvider

interface GetFlightsUrlFormatter {

    fun provideUrl(url: String, page: Int = 0): String

}

class GetFlightsUrlFormatterImpl(private val apiKeyProvider: ApiKeyProvider) : GetFlightsUrlFormatter {

    val apiKey = apiKeyProvider.provideApiKey()

    //TODO stronger url type. Url instead of String
    override fun provideUrl(url: String, page: Int) = url.plus("?apiKey=$apiKey&pageIndex=$page")

}