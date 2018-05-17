package test.scyscanner.com.scyscannertest.module.flights.item

import android.os.Parcelable
import com.avito.konveyor.blueprint.Item
import kotlinx.android.parcel.Parcelize

@Parcelize
class ItineraryItem(override val id: Long,
                    val outboundLeg: LegItem,
                    val inboundLeg: LegItem,
                    val price: String,
                    val agent: String) : Item, Parcelable {


    @Parcelize
    class LegItem(val title: String,
                  val subtitle: String,
                  val flightType: String,
                  val flightTime: String) : Parcelable
}
