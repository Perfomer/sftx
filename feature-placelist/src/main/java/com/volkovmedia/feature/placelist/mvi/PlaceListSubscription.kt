package com.volkovmedia.feature.placelist.mvi

import com.volkovmedia.core.data.datasource.database.entity.Place

internal sealed class PlaceListSubscription {

    /**
     * Network refreshing failed
     *
     * Displays the SnackBar with retry button
     *
     * @property error cause of fail
     */
    class NetworkRefreshingFailed(val error: Throwable): PlaceListSubscription()

    /**
     * Place deleted
     *
     * Displays the SnackBar with cancel button
     *
     * @property place deleted [Place]
     */
    class PlaceDeleted(val place: Place): PlaceListSubscription()

}