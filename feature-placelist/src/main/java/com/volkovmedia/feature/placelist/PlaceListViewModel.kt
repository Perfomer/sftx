package com.volkovmedia.feature.placelist

import com.volkovmedia.core.common.mvi.MviViewModel
import com.volkovmedia.core.common.util.toObservable
import com.volkovmedia.core.data.repository.PlaceRepository
import com.volkovmedia.feature.placelist.mvi.PlaceListAction
import com.volkovmedia.feature.placelist.mvi.PlaceListIntent
import com.volkovmedia.feature.placelist.mvi.PlaceListState
import com.volkovmedia.feature.placelist.mvi.PlaceListSubscription

internal class PlaceListViewModel(
    private val repository: PlaceRepository
) : MviViewModel<PlaceListIntent, PlaceListAction, PlaceListState, PlaceListSubscription>(
    initialState = PlaceListState()
) {

    init {
        postIntent(PlaceListIntent.RefreshWithNetwork)
    }

    override fun act(state: PlaceListState, intent: PlaceListIntent) = when (intent) {
        PlaceListIntent.RefreshWithNetwork -> repository.synchronizeWithNetwork()
            .andThen(PlaceListAction.RefreshingWithNetworkSucceed.toObservable<PlaceListAction>())
            .startWith(PlaceListAction.RefreshingWithNetworkStarted)
            .onErrorReturn { PlaceListAction.RefreshingWithNetworkFailed(it) }

        is PlaceListIntent.LoadData -> repository.getPlaces(intent.filterQuery)
            .asFlowSource(PlaceListIntent.LoadData::class)
            .map<PlaceListAction> { PlaceListAction.DataReceived(it) }

        is PlaceListIntent.DeletePlace -> repository.removePlace(intent.place)
            .andThen(PlaceListAction.PlaceDeleted(intent.place).toObservable())

        is PlaceListIntent.AddPlace -> repository.insertPlace(intent.place)
            .andThen(super.act(state, intent))
    }

    override fun reduce(oldState: PlaceListState, action: PlaceListAction) = when (action) {
        PlaceListAction.RefreshingWithNetworkStarted -> oldState.copy(isLoading = true)
        PlaceListAction.RefreshingWithNetworkSucceed -> oldState.copy(isLoading = false)
        is PlaceListAction.DataReceived -> oldState.copy(payload = action.payload)
        else -> super.reduce(oldState, action)
    }


    override fun publishSubscription(state: PlaceListState, action: PlaceListAction) = when (action) {
        is PlaceListAction.RefreshingWithNetworkFailed -> PlaceListSubscription.NetworkRefreshingFailed(action.error)
        is PlaceListAction.PlaceDeleted -> PlaceListSubscription.PlaceDeleted(action.place)
        else -> super.publishSubscription(state, action)
    }

}