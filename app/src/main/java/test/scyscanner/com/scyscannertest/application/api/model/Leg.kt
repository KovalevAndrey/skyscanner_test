package test.scyscanner.com.scyscannertest.application.api.model

import com.google.gson.annotations.SerializedName

class Leg(
        @SerializedName("Id") val id: String,
        @SerializedName("OriginStation") val ogiginStation: Int,
        @SerializedName("DestinationStation") val destinationStation: Int,
        @SerializedName("Departure") val departure: String,
        @SerializedName("Arrival") val arrival: String,
        @SerializedName("Duration") val duration: Int,
        @SerializedName("Stops") val stops: List<Int>,
        @SerializedName("Carriers") val carriers: List<Int>)