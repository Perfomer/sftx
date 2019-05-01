package com.volkovmedia.feature.placedetails

import com.volkovmedia.core.common.mvi.MviViewModel
import com.volkovmedia.core.common.util.toObservable
import com.volkovmedia.core.data.repository.PlaceRepository
import com.volkovmedia.feature.placedetails.mvi.PlaceDetailsAction
import com.volkovmedia.feature.placedetails.mvi.PlaceDetailsIntent
import com.volkovmedia.feature.placedetails.mvi.PlaceDetailsState
import com.volkovmedia.feature.placedetails.mvi.PlaceDetailsSubscription

internal class PlaceDetailsViewModel(
    private val placeId: String,
    private val repository: PlaceRepository
) : MviViewModel<PlaceDetailsIntent, PlaceDetailsAction, PlaceDetailsState, PlaceDetailsSubscription>(
    initialState = PlaceDetailsState()
) {

    override fun act(state: PlaceDetailsState, intent: PlaceDetailsIntent) = when (intent) {
        PlaceDetailsIntent.LoadData -> repository.getPlace(placeId)
            .toObservable()
            .map<PlaceDetailsAction> { PlaceDetailsAction.DataLoadingSucceed(it) }
            .startWith(PlaceDetailsAction.DataLoadingStarted)
            .onErrorReturn { PlaceDetailsAction.DataLoadingFailed(it) }

        PlaceDetailsIntent.DeletePlace -> repository.removePlace(state.place)
            .andThen(PlaceDetailsAction.PlaceDeleted.toObservable())
    }

    override fun reduce(oldState: PlaceDetailsState, action: PlaceDetailsAction) = when (action) {
        PlaceDetailsAction.DataLoadingStarted -> oldState.copy(isLoading = true)
        is PlaceDetailsAction.DataLoadingSucceed -> oldState.copy(isLoading = false, place = action.place)
        else -> super.reduce(oldState, action)
    }

    override fun publishSubscription(state: PlaceDetailsState, action: PlaceDetailsAction) = when (action) {
        is PlaceDetailsAction.DataLoadingFailed -> PlaceDetailsSubscription.DataLoadingFailed(action.error)
        PlaceDetailsAction.PlaceDeleted -> PlaceDetailsSubscription.PlaceDeleted
        else -> super.publishSubscription(state, action)
    }

}