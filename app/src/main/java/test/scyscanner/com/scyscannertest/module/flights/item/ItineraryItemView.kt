package test.scyscanner.com.scyscannertest.module.flights.item

import android.view.View
import android.widget.TextView
import com.avito.konveyor.adapter.BaseViewHolder
import com.avito.konveyor.blueprint.ItemView
import test.scyscanner.com.scyscannertest.R

interface ItineraryItemView : ItemView {

    fun setOutBoundTitle(text: String)

    fun setOutBoundSubtitle(text: String)

    fun setOutBoundFlightType(text: String)

    fun setOutBoundFlightLength(text: String)

    fun setInBoundTitle(text: String)

    fun setInBoundSubtitle(text: String)

    fun setInBoundFlightType(text: String)

    fun setInBoundFlightLength(text: String)

    fun setPrice(text: String)

    fun setAgent(text: String)

}

class ItineraryItemViewImpl(view: View) : BaseViewHolder(view), ItineraryItemView {

    private val outBoundTitle: TextView = view.findViewById(R.id.outbound_flight_time)
    private val outBoundSubtitle: TextView = view.findViewById(R.id.outbound_carrier)
    private val outBoundFlightType: TextView = view.findViewById(R.id.outbound_flight_type)
    private val outBoundFlightLength: TextView = view.findViewById(R.id.outbound_flight_length)
    private val inBoundTitle: TextView = view.findViewById(R.id.inbound_flight_time)
    private val inBoundSubtitle: TextView = view.findViewById(R.id.inbound_carrier)
    private val inBoundFlightType: TextView = view.findViewById(R.id.inbound_flight_type)
    private val inBoundFlightLength: TextView = view.findViewById(R.id.inbound_flight_length)
    private val price: TextView = view.findViewById(R.id.price)
    private val agent: TextView = view.findViewById(R.id.agent)

    override fun setOutBoundTitle(text: String) {
        outBoundTitle.text = text
    }

    override fun setOutBoundSubtitle(text: String) {
        outBoundSubtitle.text = text
    }

    override fun setOutBoundFlightType(text: String) {
        outBoundFlightType.text = text
    }

    override fun setOutBoundFlightLength(text: String) {
        outBoundFlightLength.text = text
    }

    override fun setInBoundTitle(text: String) {
        inBoundTitle.text = text
    }

    override fun setInBoundSubtitle(text: String) {
        inBoundSubtitle.text = text
    }

    override fun setInBoundFlightType(text: String) {
        inBoundFlightType.text = text
    }

    override fun setInBoundFlightLength(text: String) {
        inBoundFlightLength.text = text
    }

    override fun setPrice(text: String) {
        price.text = text
    }

    override fun setAgent(text: String) {
        agent.text = text
    }
}
