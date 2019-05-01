package com.volkovmedia.core.data.repository

import org.koin.dsl.module.module

val repositoryModule = module {
    single { PlaceProvider(get(), get()) } bind PlaceRepository::class
}