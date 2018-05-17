package test.scyscanner.com.scyscannertest.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import test.scyscanner.com.scyscannertest.application.TestApp
import test.scyscanner.com.scyscannertest.application.api.DeviceIpProvider
import test.scyscanner.com.scyscannertest.application.api.DeviceIpProviderImpl
import test.scyscanner.com.scyscannertest.utils.SchedulersFactory
import test.scyscanner.com.scyscannertest.utils.SchedulersFactoryImpl
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class ApplicationModule(private val app: TestApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideAvitoApp(): TestApp {
        return app
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app.applicationContext
    }


    @Provides
    @Singleton
    fun provideSchedulersFactory(): SchedulersFactory {
        return SchedulersFactoryImpl()
    }

    @Provides
    @Singleton
    fun provideIpProvider(): DeviceIpProvider = DeviceIpProviderImpl()

}