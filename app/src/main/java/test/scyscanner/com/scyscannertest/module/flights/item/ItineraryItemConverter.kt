package test.scyscanner.com.scyscannertest.module.flights.item

import test.scyscanner.com.scyscannertest.application.api.model.Leg
import test.scyscanner.com.scyscannertest.module.flights.FlightsResultConverted
import java.text.SimpleDateFormat
import java.util.*


interface ItineraryItemConverter {

    fun convertItems(flightsResult: FlightsResultConverted): List<ItineraryItem>

}

class ItineraryItemConverterImpl : ItineraryItemConverter {

    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    private val hhmmFormat = SimpleDateFormat("HH:mm")

    init {
        format.timeZone = TimeZone.getTimeZone("GMT")
    }


    private var count: Long = 0L

    override fun convertItems(flightsResult: FlightsResultConverted): List<ItineraryItem> {
        val result = mutableListOf<ItineraryItem>()

        val itinery = flightsResult.itinerary
        val legs = flightsResult.legs
        val carriers = flightsResult.carriers
        val agents = flightsResult.agents
        val currencies = flightsResult.currencies
        val places = flightsResult.places

        itinery.forEach {

            val outboundLeg: Leg = legs[it.outBoundLegId] ?: return@forEach
            val outboundFlightType = if (outboundLeg.stops.isEmpty()) {
                "Direct"
            } else {
                "Indirect"
            }
            val outboundLegDuration = outboundLeg.duration

            val hoursOutbound = outboundLegDuration / 60
            val minutesOutbound = outboundLegDuration % 60
            val durationTitlteOutbound = if (hoursOutbound > 0) {
                "${hoursOutbound}h ${minutesOutbound}m"
            } else {
                "${minutesOutbound}m"
            }


            val outBoundDepartureTime: Date = format.parse(outboundLeg.departure)
            val outBoundArrivalTime: Date = format.parse(outboundLeg.arrival)

            val outBoundTitle = hhmmFormat.format(outBoundDepartureTime)
                    .plus(" - ")
                    .plus(hhmmFormat.format(outBoundArrivalTime))

            val outboundOriginStationTitle = places[outboundLeg.ogiginStation]?.code
                    ?: return@forEach
            val outboundDestinationStationTitle = places[outboundLeg.destinationStation]?.code
                    ?: return@forEach
            val outBoundCarrier = carriers[outboundLeg.carriers.first()]?.name ?: return@forEach

            val outBoundSubtitle: String = outboundOriginStationTitle.plus(" - ").plus(outboundDestinationStationTitle).plus(", $outBoundCarrier")

            val legItemOutBound = ItineraryItem.LegItem(outBoundTitle, outBoundSubtitle, outboundFlightType, durationTitlteOutbound)

            val inboundLeg: Leg = legs[it.inboundLegId] ?: return@forEach
            val inboundFlightType = if (inboundLeg.stops.isEmpty()) {
                "Direct"
            } else {
                "Indirect"
            }
            val inboundLegDuration = inboundLeg.duration
            val hours = inboundLegDuration / 60
            val minutes = inboundLegDuration % 60
            val durationTitlte = if (hours > 0) {
                "${hours}h ${minutes}m"
            } else {
                "${minutes}m"
            }

            val inBoundDepartureTime: Date = format.parse(inboundLeg.departure)
            val inBoundArrivalTime: Date = format.parse(inboundLeg.arrival)

            val inBoundTitle = hhmmFormat.format(inBoundDepartureTime)
                    .plus(" - ")
                    .plus(hhmmFormat.format(inBoundArrivalTime))

            val inboundOriginStationTitle = places[inboundLeg.ogiginStation]?.code ?: return@forEach
            val inboundDestinationStationTitle = places[inboundLeg.destinationStation]?.code
                    ?: return@forEach
            val inBoundCarrier = carriers[inboundLeg.carriers.first()]?.name ?: return@forEach

            val inBoundSubtitle: String = inboundOriginStationTitle.plus(" - ").plus(inboundDestinationStationTitle).plus(", $inBoundCarrier")

            val legItemInbound = ItineraryItem.LegItem(inBoundTitle, inBoundSubtitle, inboundFlightType, durationTitlte)

            val pricingOption = it.pricingOptions.first()

            val price = pricingOption.price.toString().plus(currencies.first().symbol)
            val agentsList = pricingOption.agents
            if (agentsList.isEmpty()) return@forEach
            val agentName = agents[agentsList.first()]?.name ?: return@forEach

            result.add(ItineraryItem(count++, legItemOutBound, legItemInbound, price, agentName))
        }

        return result
    }

}