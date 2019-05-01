package com.volkovmedia.feature.placelist.mvi

import com.volkovmedia.core.data.datasource.database.entity.Place

internal data class PlaceListState(
    /**
     * Is loading from the network currently in progress
     */
    val isLoading: Boolean = false,

    /**
     * [Place]s list
     */
    val payload: List<Place> = emptyList()
)