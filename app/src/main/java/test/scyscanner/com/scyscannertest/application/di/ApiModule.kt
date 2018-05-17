package test.scyscanner.com.scyscannertest.application.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import test.scyscanner.com.scyscannertest.application.api.*
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonFactory().createGson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context,
                            interceptor: HeaderInterceptor,
                            apiKeyInterceptor: ApiKeyInterceptor,
                            loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.cache(Cache(File(context.cacheDir, CACHE_DIR), CACHE_SIZE))
        builder.addInterceptor(interceptor)
        builder.addInterceptor(loggingInterceptor)
        builder.addInterceptor(apiKeyInterceptor)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(apiKeyProvider: ApiKeyProvider) = ApiKeyInterceptor(apiKeyProvider)

    @Provides
    @Singleton
    fun provideHeaderInterceptor(deviceIpProvider: DeviceIpProvider) = HeaderInterceptor(deviceIpProvider)

    @Provides
    @Singleton
    fun provideApiKeyProvider(context: Context): ApiKeyProvider {
        return ApiKeyProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient,
                   gson: Gson): SkyscannerApi {

        val builder = Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
        return builder
                .build()
                .create(SkyscannerApi::class.java)

    }

}

private const val CACHE_DIR = "http"
private const val CACHE_SIZE = 10L * 1024L * 1024L
private const val HOST = "http://partners.api.skyscanner.net/"