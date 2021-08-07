package com.github.raininforest.stocks.mvp.presenter

import com.github.raininforest.stocks.di.IStocksScopeContainer
import com.github.raininforest.stocks.mvp.model.entity.Stock
import com.github.raininforest.stocks.mvp.model.navigation.IScreens
import com.github.raininforest.stocks.mvp.model.repo.IStocksRepository
import com.github.raininforest.stocks.mvp.presenter.list.IStocksListItemPresenter
import com.github.raininforest.stocks.mvp.view.StocksView
import com.github.raininforest.stocks.mvp.view.list.StockItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

/**
 * Created by Sergey Velesko on 31.07.2021
 */
class StocksPresenter : MvpPresenter<StocksView>() {

    @Inject
    lateinit var stocksScopeContainer: IStocksScopeContainer

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var stocksRepo: IStocksRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    class StocksListPresenter : IStocksListItemPresenter {
        val stocks = mutableListOf<Stock>()
        override var itemClickListener: ((StockItemView) -> Unit)? = null

        override fun getCount() = stocks.size

        override fun bindView(view: StockItemView) {
            val stock = stocks[view.pos]
            stock.ticker.let { view.setTicker(it) }
            stock.companyName.let { view.setCompany(it) }
            stock.price.let { view.setPrice(it) }
            stock.change.let { view.setChange(it) }
            stock.logoUrl.let { view.setLogo(it) }
        }
    }

    val stocksListPresenter = StocksListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        stocksListPresenter.itemClickListener = { itemView ->
            val ticker = stocksListPresenter.stocks[itemView.pos].ticker
            router.navigateTo(screens.news(ticker))
        }
    }

    private fun loadData() {
        stocksRepo.getStocks()
            .observeOn(uiScheduler)
            .subscribe({ stocks ->
                stocksListPresenter.stocks.clear()
                stocksListPresenter.stocks.addAll(stocks)
                viewState.updateList()
            }, {
                println("[ Error ]: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        stocksScopeContainer.releaseStocksScope()
        super.onDestroy()
    }
}