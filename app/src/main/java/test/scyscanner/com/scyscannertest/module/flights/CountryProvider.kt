package test.scyscanner.com.scyscannertest.module.flights

import android.content.Context
import android.os.Build

interface CountryProvider {

    fun provideCountry() :String
}

class CountryProviderImpl(private val context: Context) : CountryProvider {

    override fun provideCountry() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0].country
    } else {
        context.resources.configuration.locale.country
    }

}