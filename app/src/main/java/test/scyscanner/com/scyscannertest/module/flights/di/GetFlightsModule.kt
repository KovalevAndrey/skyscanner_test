package test.scyscanner.com.scyscannertest.module.flights.di

import android.content.Context
import android.os.Bundle
import com.avito.konveyor.ItemBinder
import com.avito.konveyor.adapter.AdapterPresenter
import com.avito.konveyor.adapter.SimpleAdapterPresenter
import dagger.Module
import dagger.Provides
import test.scyscanner.com.scyscannertest.application.api.ApiKeyProvider
import test.scyscanner.com.scyscannertest.application.api.SkyscannerApi
import test.scyscanner.com.scyscannertest.module.flights.*
import test.scyscanner.com.scyscannertest.module.flights.item.*
import test.scyscanner.com.scyscannertest.utils.SchedulersFactory

@Module
class GetFlightsModule(private val context: Context,
                       private val presenterState: Bundle?) {

    @Provides
    @PerActivity
    internal fun provideFlightsPresenter(interactor: GetFlightsInteractor,
                                         schedulersFactory: SchedulersFactory,
                                         adapterPresenter: AdapterPresenter,
                                         journeyDatesProvider: JourneyDatesProvider,
                                         converter: ItineraryItemConverter): GetFlightsPresenter {
        return GetFlightsPresenterImpl(interactor, schedulersFactory,
                converter, journeyDatesProvider, adapterPresenter, presenterState)
    }


    @Provides
    @PerActivity
    internal fun provideGetFlightsInteractorImpl(api: SkyscannerApi,
                                                 countryProvider: CountryProvider,
                                                 schedulersFactory: SchedulersFactory,
                                                 searchResultConverter: SearchResultConverter,
                                                 apiKeyProvider: ApiKeyProvider): GetFlightsInteractor {
        return GetFlightsInteractorImpl(api, countryProvider, schedulersFactory,
                searchResultConverter, apiKeyProvider)
    }

    @Provides
    @PerActivity
    internal fun provideCountryProvider(): CountryProvider {
        return CountryProviderImpl(context)
    }

    @Provides
    @PerActivity
    internal fun provideJourneyDatesProvider(): JourneyDatesProvider {
        return JourneyDatesProviderImpl()
    }

    @Provides
    @PerActivity
    internal fun provideItineraryItemConverter(): ItineraryItemConverter {
        return ItineraryItemConverterImpl()
    }

    @Provides
    @PerActivity
    internal fun provideSearchResultConverter(): SearchResultConverter {
        return SearchResultConverterImpl()
    }

    @Provides
    @PerActivity
    internal fun provideAdapterPresenter(itemBinder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(itemBinder, itemBinder)
    }

    @Provides
    @PerActivity
    internal fun provideItineraryItemPresenter(): ItineraryItemPresenter {
        return ItineraryItemPresenterImpl()
    }

    @Provides
    @PerActivity
    internal fun provideItineraryItemBlueprint(itineraryItemPresenter: ItineraryItemPresenter): ItineraryItemBlueprint {
        return ItineraryItemBlueprint(itineraryItemPresenter)
    }

    @Provides
    @PerActivity
    internal fun provideItemBinder(itemBlueprint: ItineraryItemBlueprint): ItemBinder {
        val builder = ItemBinder.Builder()
                .registerItem(itemBlueprint)
        return builder.build()
    }
}
