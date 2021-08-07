package com.github.raininforest.stocks.di.module

import com.github.raininforest.stocks.App
import com.github.raininforest.stocks.mvp.model.api.IRemoteApi
import com.github.raininforest.stocks.mvp.model.network.INetworkStatus
import com.github.raininforest.stocks.ui.network.NetworkStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Sergey Velesko on 31.07.2021
 */
@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://finnhub.io/api/v1/"

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .create()

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson, client: OkHttpClient): IRemoteApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IRemoteApi::class.java)

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = NetworkStatus(app)
}