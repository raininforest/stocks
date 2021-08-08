package com.github.raininforest.stocks.mvp.presenter

import com.github.raininforest.stocks.di.INewsScopeContainer
import com.github.raininforest.stocks.mvp.model.entity.News
import com.github.raininforest.stocks.mvp.model.repo.INewsRepository
import com.github.raininforest.stocks.mvp.presenter.list.INewsListItemPresenter
import com.github.raininforest.stocks.mvp.view.NewsView
import com.github.raininforest.stocks.mvp.view.list.NewsItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

/**
 * Created by Sergey Velesko on 31.07.2021
 */
class NewsPresenter(val ticker: String) : MvpPresenter<NewsView>() {

    @Inject
    lateinit var repositoryScopeContainer: INewsScopeContainer

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var newsRepository: INewsRepository

    @Inject
    lateinit var router: Router

    class NewsListItemPresenter : INewsListItemPresenter {
        val news = mutableListOf<News>()

        override var itemClickListener: ((NewsItemView) -> Unit)? = null

        override fun getCount() = news.size

        override fun bindView(view: NewsItemView) {
            val newsItem = news[view.pos]

            view.setHeader(newsItem.header)
            view.setSummary(newsItem.summary)
            view.setSource(newsItem.source)
        }
    }

    val newsListItemPresenter = NewsListItemPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showLoading()
        loadData()

        newsListItemPresenter.itemClickListener = { itemView ->
            val newsItem = newsListItemPresenter.news[itemView.pos]
            viewState.openLink(newsItem.url)
        }
    }

    private fun loadData() {
        newsRepository.getNews(ticker)
            .observeOn(uiScheduler)
            .subscribe({ repositories ->
                newsListItemPresenter.news.clear()
                newsListItemPresenter.news.addAll(repositories)
                viewState.updateList()
                viewState.hideLoading()
            }, {
                println("[ News ERROR ]: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        repositoryScopeContainer.releaseNewsScope()
        super.onDestroy()
    }
}