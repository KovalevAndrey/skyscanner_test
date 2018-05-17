package test.scyscanner.com.scyscannertest.module.flights.item

import com.avito.konveyor.blueprint.Item
import com.avito.konveyor.blueprint.ItemBlueprint
import com.avito.konveyor.blueprint.ItemPresenter
import com.avito.konveyor.blueprint.ViewHolderBuilder
import test.scyscanner.com.scyscannertest.R

class ItineraryItemBlueprint(override val presenter: ItemPresenter<ItineraryItemView, ItineraryItem>) :
        ItemBlueprint<ItineraryItemView, ItineraryItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.itinerary_item,
            creator = { _, view -> ItineraryItemViewImpl(view) }
    )

    override fun isRelevantItem(item: Item) = item is ItineraryItem

}