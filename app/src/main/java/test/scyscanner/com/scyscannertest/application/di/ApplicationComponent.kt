package test.scyscanner.com.scyscannertest.application.di

import dagger.Component
import test.scyscanner.com.scyscannertest.application.TestApp
import test.scyscanner.com.scyscannertest.module.flights.di.GetFlightsComponent
import test.scyscanner.com.scyscannertest.module.flights.di.GetFlightsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(app: TestApp)

    fun plus(getFlightsModule: GetFlightsModule): GetFlightsComponent

}