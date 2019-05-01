package com.volkovmedia.core.data.datasource.database

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val databaseModule = module {
    single { SftxDatabase.getInstance(androidContext(), "db") }
    single { get<SftxDatabase>().getPlaceDao() }
}