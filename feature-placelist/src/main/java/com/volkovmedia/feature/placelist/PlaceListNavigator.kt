package com.volkovmedia.feature.placelist

interface PlaceListNavigator {

    /**
     * Navigation request to the [Place] details screen
     *
     * @param id [Place] identifier
     */
    fun navigateToPlaceDetails(id: String)

}