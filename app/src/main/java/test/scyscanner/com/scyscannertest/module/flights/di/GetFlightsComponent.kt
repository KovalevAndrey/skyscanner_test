package test.scyscanner.com.scyscannertest.module.flights.di

import dagger.Subcomponent
import test.scyscanner.com.scyscannertest.module.flights.ui.GetFlightsActivity

@Subcomponent(modules = arrayOf(GetFlightsModule::class))
@PerActivity
interface GetFlightsComponent {

    fun inject(activity: GetFlightsActivity)
}