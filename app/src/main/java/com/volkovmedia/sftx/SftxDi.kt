package com.volkovmedia.sftx

import com.volkovmedia.core.data.datasource.database.databaseModule
import com.volkovmedia.core.data.datasource.network.networkModule
import com.volkovmedia.core.data.repository.repositoryModule
import com.volkovmedia.feature.placelist.placeListModule
import com.volkovmedia.sftx.navigation.navigationModule
import org.koin.dsl.module.module

val koinModules = listOf(
    navigationModule,

    databaseModule,
    networkModule,
    repositoryModule,

    placeListModule
)