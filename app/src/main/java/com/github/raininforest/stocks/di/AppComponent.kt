package com.github.raininforest.stocks.di

import com.github.raininforest.stocks.di.module.ApiModule
import com.github.raininforest.stocks.di.module.CacheModule
import com.github.raininforest.stocks.di.module.CiceroneModule
import com.github.raininforest.stocks.di.module.RepoModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sergey Velesko on 31.07.2021
 */
@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    //TODO
}