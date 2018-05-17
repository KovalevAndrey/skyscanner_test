package test.scyscanner.com.scyscannertest.application.api.model

import com.google.gson.annotations.SerializedName

class FlightsSearchResult(@SerializedName("Itineraries") val itineraries: List<Itinerary>,
                          @SerializedName("Legs") val legs: List<Leg>,
                          @SerializedName("Carriers") val carriers: List<Carrier>,
                          @SerializedName("Agents") val agents: List<Agent>,
                          @SerializedName("Places") val places: List<Place>,
                          @SerializedName("Status") val status: String,
                          @SerializedName("Currencies") val currencies: List<Currency>)