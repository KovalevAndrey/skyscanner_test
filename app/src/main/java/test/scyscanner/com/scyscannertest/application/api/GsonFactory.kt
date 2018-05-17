package test.scyscanner.com.scyscannertest.application.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonFactory {

    fun createGson(): Gson {
        return GsonBuilder()
                .create()

    }
}