package test.scyscanner.com.scyscannertest.module.flights.item

import com.avito.konveyor.blueprint.ItemPresenter

interface ItineraryItemPresenter : ItemPresenter<ItineraryItemView, ItineraryItem>

class ItineraryItemPresenterImpl : ItineraryItemPresenter {

    override fun bindView(view: ItineraryItemView, item: ItineraryItem, position: Int) {
        with(view) {
            setAgent(item.agent)
            setPrice(item.price)
            val outboundLeg = item.outboundLeg
            setOutBoundTitle(outboundLeg.title)
            setOutBoundSubtitle(outboundLeg.subtitle)
            setOutBoundFlightType(outboundLeg.flightType)
            setOutBoundFlightLength(outboundLeg.flightTime)

            val inboundLeg = item.inboundLeg
            setInBoundTitle(inboundLeg.title)
            setInBoundSubtitle(inboundLeg.subtitle)
            setInBoundFlightType(inboundLeg.flightType)
            setInBoundFlightLength(inboundLeg.flightTime)
        }

    }

}