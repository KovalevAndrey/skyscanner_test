package test.scyscanner.com.scyscannertest.application.api.model

import com.google.gson.annotations.SerializedName

class Agent(
        @SerializedName("Id") val id: Int,
        @SerializedName("Name") val name: String)
