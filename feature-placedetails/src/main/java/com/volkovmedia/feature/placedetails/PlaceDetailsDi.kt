package com.volkovmedia.feature.placedetails

import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val placeDetailsModule = module {
    viewModel { (placeId: String) -> PlaceDetailsViewModel(placeId, get()) }

    factory(DI_FRAGMENT_PLACEDETAILS) { (placeId: String) ->
        PlaceDetailsFragment.newInstance(placeId)
    } bind Fragment::class
}

const val DI_FRAGMENT_PLACEDETAILS = "PlaceDetailsFragment"