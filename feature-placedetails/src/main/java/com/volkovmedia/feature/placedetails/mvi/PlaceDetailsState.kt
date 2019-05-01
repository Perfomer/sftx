package com.volkovmedia.feature.placedetails.mvi

import com.volkovmedia.core.common.util.currentDate
import com.volkovmedia.core.data.datasource.database.entity.Place

internal data class PlaceDetailsState(
    /**
     * Is loading from the network currently in progress
     */
    val isLoading: Boolean = false,

    /**
     * [Place] info
     */
    val place: Place = Place("", currentDate, "", null)
)