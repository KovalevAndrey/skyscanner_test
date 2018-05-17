package test.scyscanner.com.scyscannertest.application

import android.app.Application
import test.scyscanner.com.scyscannertest.application.di.ApplicationComponent
import test.scyscanner.com.scyscannertest.application.di.ApplicationModule
import test.scyscanner.com.scyscannertest.application.di.DaggerApplicationComponent

class TestApp : Application() {

    lateinit var component: ApplicationComponent

    companion object {
        lateinit var instance: TestApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }

}