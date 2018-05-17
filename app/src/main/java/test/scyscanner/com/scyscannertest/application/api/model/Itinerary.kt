package test.scyscanner.com.scyscannertest.application.api.model

import com.google.gson.annotations.SerializedName

class Itinerary(
        @SerializedName("OutboundLegId") val outBoundLegId: String,
        @SerializedName("InboundLegId") val inboundLegId: String,
        @SerializedName("PricingOptions") val pricingOptions: List<PricingOption>) {

    class PricingOption(@SerializedName("Agents") val agents: List<Int>,
                        @SerializedName("Price") val price: Double)
}
