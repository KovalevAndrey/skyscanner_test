package test.scyscanner.com.scyscannertest.module.flights.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import com.avito.konveyor.adapter.AdapterPresenter
import com.avito.konveyor.adapter.BaseViewHolder
import com.avito.konveyor.adapter.SimpleRecyclerAdapter
import com.avito.konveyor.blueprint.ViewHolderBuilder
import test.scyscanner.com.scyscannertest.R

interface GetFlightsView {

    fun onDataSourceChanged()

    fun showLoading()

    fun setToolbarTitle(text: String)

    fun setToolbarSubTitle(text: String)

    fun hideLoading()
}


class GetFlightsViewImpl(
        view: ViewGroup,
        private val viewHolderBuilder: ViewHolderBuilder<BaseViewHolder>,
        private val adapterPresenter: AdapterPresenter
) : GetFlightsView {

    private val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
    private val progressBar: View = view.findViewById(R.id.progress)
    private val toolbar: Toolbar = view.findViewById(R.id.toolbar)
    private val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

    init {
        val white = ContextCompat.getColor(toolbar.context, R.color.colorWhite)
        toolbar.setTitleTextColor(white)
        toolbar.setSubtitleTextColor(white)
        toolbar.navigationIcon = ContextCompat.getDrawable(toolbar.context, R.drawable.ic_back)
        recyclerView.layoutManager = layoutManager
    }

    override fun setToolbarTitle(text: String) {
        toolbar.title = text
    }

    override fun setToolbarSubTitle(text: String) {
        toolbar.subtitle = text
    }

    override fun onDataSourceChanged() {
        if (recyclerView.adapter == null) {
            val adapter = SimpleRecyclerAdapter(adapterPresenter, viewHolderBuilder)
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
        } else {
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}