package test.scyscanner.com.scyscannertest.module.flights.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avito.konveyor.ItemBinder
import com.avito.konveyor.adapter.AdapterPresenter
import test.scyscanner.com.scyscannertest.R
import test.scyscanner.com.scyscannertest.application.TestApp
import test.scyscanner.com.scyscannertest.module.flights.GetFlightsPresenter
import test.scyscanner.com.scyscannertest.module.flights.di.GetFlightsModule
import javax.inject.Inject

class GetFlightsActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: GetFlightsPresenter
    @Inject
    lateinit var adapterPresenter: AdapterPresenter
    @Inject
    lateinit var itemBinder: ItemBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenterState = savedInstanceState?.getBundle(KEY_PRESENTER)
        setContentView(R.layout.flights_activity)
        TestApp.instance.component.plus(GetFlightsModule(this.baseContext, presenterState)).inject(this)
        val extensionView = GetFlightsViewImpl(findViewById(R.id.content), itemBinder, adapterPresenter)
        presenter.attachView(extensionView)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBundle(KEY_PRESENTER, presenter.onSaveState())
    }
}

private const val KEY_PRESENTER = "key_presenter"