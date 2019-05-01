package com.volkovmedia.feature.placedetails.mvi

import com.volkovmedia.core.data.datasource.database.entity.Place

internal sealed class PlaceDetailsAction {

    /**
     * Data loading started
     *
     * Displays ProgressBar
     */
    object DataLoadingStarted: PlaceDetailsAction()

    /**
     * Data loading finished successfully
     *
     * Displays [Place] info
     *
     * @property place result of loading
     */
    class DataLoadingSucceed(val place: Place): PlaceDetailsAction()

    /**
     * Data loading finished with error
     *
     * Causes screen closing
     *
     * @property error cause of fail
     */
    class DataLoadingFailed(val error: Throwable): PlaceDetailsAction()

    /**
     * Place deleted
     *
     * Causes screen closing
     */
    object PlaceDeleted: PlaceDetailsAction()

}