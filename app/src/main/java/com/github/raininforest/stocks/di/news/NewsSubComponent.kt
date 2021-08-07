package com.github.raininforest.stocks.di.news

import com.github.raininforest.stocks.di.news.module.NewsModule
import com.github.raininforest.stocks.mvp.presenter.NewsPresenter
import dagger.Subcomponent

/**
 * Created by Sergey Velesko on 07.08.2021
 */
@NewsScope
@Subcomponent(
    modules = [NewsModule::class]
)
interface NewsSubComponent {

    fun inject(newsPresenter: NewsPresenter)
}