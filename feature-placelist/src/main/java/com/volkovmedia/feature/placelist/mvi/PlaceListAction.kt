package com.volkovmedia.feature.placelist.mvi

import com.volkovmedia.core.data.datasource.database.entity.Place

internal sealed class PlaceListAction {

    /**
     * Refreshing with Network started
     *
     * Displays the Progressbar
     */
    object RefreshingWithNetworkStarted : PlaceListAction()

    /**
     * Refreshing with Network finished successful
     *
     * Hides the Progressbar
     */
    object RefreshingWithNetworkSucceed : PlaceListAction()

    /**
     * Refreshing with Network finished successful
     *
     * Hides the Progressbar, shows SnackBar with retry button
     */
    class RefreshingWithNetworkFailed(val error: Throwable) : PlaceListAction()

    /**
     * Requesting data from the local database finished successful
     *
     * Displays list of [Place]s
     *
     * @property payload list of [Place]s
     */
    class DataReceived(val payload: List<Place>): PlaceListAction()

    /**
     * Place deleted
     *
     * Displays SnackBar with cancel button
     *
     * @property place deleted [Place]
     */
    class PlaceDeleted(val place: Place): PlaceListAction()

}