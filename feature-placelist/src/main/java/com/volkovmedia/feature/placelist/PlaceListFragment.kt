package com.volkovmedia.feature.placelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.volkovmedia.core.common.mvi.MviFragment
import com.volkovmedia.core.common.util.createSnackbarWithAction
import com.volkovmedia.core.common.util.init
import com.volkovmedia.core.data.datasource.database.entity.Place
import com.volkovmedia.feature.placelist.mvi.PlaceListIntent
import com.volkovmedia.feature.placelist.mvi.PlaceListState
import com.volkovmedia.feature.placelist.mvi.PlaceListSubscription
import com.volkovmedia.feature.placelist.recycler.PlaceListAdapter
import com.volkovmedia.feature.placelist.recycler.PlaceListItemTouchHelperCallback
import kotlinx.android.synthetic.main.placelist_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

internal class PlaceListFragment : MviFragment<PlaceListIntent, PlaceListState, PlaceListSubscription>(
    initialIntent = PlaceListIntent.LoadData("")
) {

    override val layoutResource = R.layout.placelist_fragment


    private val navigator by lazy { activity as PlaceListNavigator }

    private val adapter = PlaceListAdapter(::onPlaceClick)

    override fun provideViewModel() = getViewModel<PlaceListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placelist_recycler.init(
            adapter = adapter,
            itemTouchHelper = PlaceListItemTouchHelperCallback(adapter, ::onPlaceSwiped)
        )

        placelist_toolbar.attachToActivity()

        placelist_swiperefresh.setOnRefreshListener { postIntent(PlaceListIntent.RefreshWithNetwork) }
    }

    override fun render(state: PlaceListState) {
        val payload = state.payload

        placelist_swiperefresh.isRefreshing = state.isLoading
        placelist_empty.isVisible = payload.isEmpty()

        adapter.items = payload
    }

    @SuppressLint("ResourceType")
    override fun onSubscriptionReceived(subscription: PlaceListSubscription) {
        when (subscription) {
            is PlaceListSubscription.NetworkRefreshingFailed -> {
                view?.createSnackbarWithAction(
                    message = R.string.placelist_refreshingerror,
                    actionText = R.string.placelist_tryagain,
                    actionTextColor = R.color.red,
                    onActionClick = { postIntent(PlaceListIntent.RefreshWithNetwork) }
                )?.show()
            }

            is PlaceListSubscription.PlaceDeleted -> {
                view?.createSnackbarWithAction(
                    message = R.string.placelist_placedeleted,
                    actionText = android.R.string.cancel,
                    onActionClick = { postIntent(PlaceListIntent.AddPlace(subscription.place)) }
                )?.show()
            }
        }
    }


    private fun onPlaceClick(place: Place) = navigator.navigateToPlaceDetails(place.id)

    private fun onPlaceSwiped(place: Place) = postIntent(PlaceListIntent.DeletePlace(place))

}