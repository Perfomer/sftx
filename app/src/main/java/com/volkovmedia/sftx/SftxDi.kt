package com.volkovmedia.sftx

import com.volkovmedia.core.data.datasource.database.databaseModule
import com.volkovmedia.core.data.datasource.network.networkModule
import com.volkovmedia.core.data.repository.repositoryModule
import org.koin.dsl.module.module

val appModule = module {

}

val koinModules = listOf(
    appModule,

    databaseModule,
    networkModule,
    repositoryModule
)