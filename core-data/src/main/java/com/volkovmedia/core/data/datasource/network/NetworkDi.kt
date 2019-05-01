package com.volkovmedia.core.data.datasource.network

import com.google.gson.GsonBuilder
import com.volkovmedia.core.data.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single { OkHttpClient.Builder().build() }

    single { GsonConverterFactory.create(get()) } bind Converter.Factory::class
    single { RxJava2CallAdapterFactory.create() } bind CallAdapter.Factory::class

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ROOT_URL)
            .client(get())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

    single { get<Retrofit>().create(SftxApi::class.java) }
}