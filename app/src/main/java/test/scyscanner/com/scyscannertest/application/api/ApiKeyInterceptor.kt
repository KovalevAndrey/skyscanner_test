package test.scyscanner.com.scyscannertest.application.api

import okhttp3.Interceptor
import okhttp3.Response
import java.net.URI


class ApiKeyInterceptor(apiKeyProvider: ApiKeyProvider) : Interceptor {

    private val apiKey = apiKeyProvider.provideApiKey()
    private val apiKeyQuery = "apiKey=$apiKey"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val uri = request.url().uri()
        var newUri: String = request.url().uri().toString()
        val method = request.method()

        if (method == "GET") {
            var query = uri.query
            if (query == null) {
                query = apiKeyQuery
            } else {
                query.plus("&").plus(apiKeyQuery)
            }
            newUri = URI(uri.scheme, uri.authority, uri.path, query, uri.fragment).toString()
            val newRequest = request.newBuilder()
                    .url(newUri)
                    .build()

            return chain.proceed(newRequest)
        }

        return chain.proceed(request)
    }

}
