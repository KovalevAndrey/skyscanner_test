package test.scyscanner.com.scyscannertest.application.api

import android.content.Context
import test.scyscanner.com.scyscannertest.R

interface ApiKeyProvider {

    fun provideApiKey(): String
}

class ApiKeyProviderImpl(private val context: Context) : ApiKeyProvider {

    override fun provideApiKey(): String {
        return context.resources.getString(R.string.api_key)
    }

}