package com.volkovmedia.feature.placelist.mvi

import com.volkovmedia.core.data.datasource.database.entity.Place

internal sealed class PlaceListIntent {

    /**
     * Refresh data with Network
     */
    object RefreshWithNetwork: PlaceListIntent()

    /**
     * Request data from local database
     *
     * @property filterQuery filter query for search by name of the [Place]
     */
    class LoadData(val filterQuery: String = ""): PlaceListIntent()

    /**
     * Delete place
     *
     * @property place place should be deleted
     */
    class DeletePlace(val place: Place): PlaceListIntent()

    /**
     * Add place
     *
     * @property place place should be added
     */
    class AddPlace(val place: Place): PlaceListIntent()

}