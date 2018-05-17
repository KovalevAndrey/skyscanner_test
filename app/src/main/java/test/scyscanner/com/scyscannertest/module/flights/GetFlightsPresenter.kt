package test.scyscanner.com.scyscannertest.module.flights

import android.os.Bundle
import android.util.Log
import com.avito.konveyor.adapter.AdapterPresenter
import com.avito.konveyor.data_source.ListDataSource
import io.reactivex.disposables.Disposable
import test.scyscanner.com.scyscannertest.module.flights.item.ItineraryItem
import test.scyscanner.com.scyscannertest.module.flights.item.ItineraryItemConverter
import test.scyscanner.com.scyscannertest.module.flights.ui.GetFlightsView
import test.scyscanner.com.scyscannertest.utils.SchedulersFactory
import test.scyscanner.com.scyscannertest.utils.asArrayList

interface GetFlightsPresenter {

    fun attachView(view: GetFlightsView)

    fun detachView()

    fun onSaveState(): Bundle

}

class GetFlightsPresenterImpl(
        private val interactor: GetFlightsInteractor,
        private val schedulersFactory: SchedulersFactory,
        private val itemConverter: ItineraryItemConverter,
        private val journeyDatesProvider: JourneyDatesProvider,
        private val adapterPresenter: AdapterPresenter,
        savedState: Bundle?
) : GetFlightsPresenter {

    private var disposables: Disposable? = null
    private var view: GetFlightsView? = null
    private var itineraryItem: List<ItineraryItem>? = savedState?.getParcelableArrayList(KEY_ITEMS)

    override fun onSaveState(): Bundle = Bundle().apply {
        putParcelableArrayList(KEY_ITEMS, itineraryItem.asArrayList())
    }

    override fun attachView(view: GetFlightsView) {
        this.view = view
        view.setToolbarTitle("Edinburgh - London")
        val subtitle = journeyDatesProvider
                .provideOutboundDateShort()
                .plus(" - ")
                .plus(journeyDatesProvider.provideInboundDateShort())
                .plus(", 1 adult, economy")
        view.setToolbarSubTitle(subtitle)
        val items = itineraryItem
        if (items == null) {
            loadData()
        } else {
            updateDataSource(items)
        }
    }

    override fun detachView() {
        disposables?.dispose()
        this.view = null
    }

    private fun updateDataSource(items: List<ItineraryItem>) {
        val dataSource = ListDataSource(items)
        adapterPresenter.onDataSourceChanged(dataSource)
        view?.onDataSourceChanged()
    }

    private fun loadData() {
        view?.showLoading()
        disposables = interactor
                .getFlights(journeyDatesProvider.provideOutboundDate(), journeyDatesProvider.provideInboundDate())
                .observeOn(schedulersFactory.mainThread())
                .subscribe({
                    handleLoadedData(it)
                }, {
                    Log.e("GetFlightsPresenter", "failed to load", it)
                    view?.hideLoading()
                })
    }

    private fun handleLoadedData(data: FlightsResultConverted) {
        val convertedItems = itemConverter.convertItems(data)
        itineraryItem = convertedItems
        updateDataSource(convertedItems)
        view?.hideLoading()
    }

}

private const val KEY_ITEMS = "key_items"