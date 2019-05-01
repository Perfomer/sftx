package com.volkovmedia.feature.placedetails.mvi

internal sealed class PlaceDetailsIntent {

    /**
     * Load data request
     */
    object LoadData: PlaceDetailsIntent()

    /**
     * Delete [Place] request
     */
    object DeletePlace: PlaceDetailsIntent()

}