package test.scyscanner.com.scyscannertest.application.api.model

import com.google.gson.annotations.SerializedName

class Currency(
        @SerializedName("Code") val code: String,
        @SerializedName("Symbol") val symbol: String)
