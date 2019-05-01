package com.volkovmedia.feature.placedetails.mvi

internal sealed class PlaceDetailsSubscription {

    /**
     * Data loading finished with error
     *
     * Causes screen closing
     *
     * @property error cause of fail
     */
    class DataLoadingFailed(val error: Throwable): PlaceDetailsSubscription()

    /**
     * Place deleted
     *
     * Causes screen closing
     */
    object PlaceDeleted: PlaceDetailsSubscription()

}