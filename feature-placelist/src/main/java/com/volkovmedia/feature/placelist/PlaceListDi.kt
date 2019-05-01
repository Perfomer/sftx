package com.volkovmedia.feature.placelist

import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val placeListModule = module {
    viewModel { PlaceListViewModel(get()) }
    factory(DI_FRAGMENT_PLACELIST) { PlaceListFragment() } bind Fragment::class
}

const val DI_FRAGMENT_PLACELIST = "PlaceListFragment"