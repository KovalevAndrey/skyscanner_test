package test.scyscanner.com.scyscannertest.application.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val deviceIpProvider: DeviceIpProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val builder = request.newBuilder()
                .addHeader("X-Forwarded-For", deviceIpProvider.provideIpAddress())
                .addHeader("Accept", "application/json")
                .build()

        return chain.proceed(builder)

    }

}
