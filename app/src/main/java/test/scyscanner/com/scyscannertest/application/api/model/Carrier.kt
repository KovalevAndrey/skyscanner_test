package test.scyscanner.com.scyscannertest.application.api.model

import com.google.gson.annotations.SerializedName

class Carrier(@SerializedName("Id") val id: Int,
              @SerializedName("Code") val code: String,
              @SerializedName("ImageUrl") val url: String,
              @SerializedName("Name") val name: String)