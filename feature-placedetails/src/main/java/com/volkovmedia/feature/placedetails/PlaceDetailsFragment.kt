package com.volkovmedia.feature.placedetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.volkovmedia.core.common.mvi.MviFragment
import com.volkovmedia.core.common.util.argumentString
import com.volkovmedia.core.common.util.format
import com.volkovmedia.core.common.util.loadImage
import com.volkovmedia.core.common.util.toast
import com.volkovmedia.core.data.datasource.database.entity.Place
import com.volkovmedia.feature.placedetails.mvi.PlaceDetailsIntent
import com.volkovmedia.feature.placedetails.mvi.PlaceDetailsState
import com.volkovmedia.feature.placedetails.mvi.PlaceDetailsSubscription
import kotlinx.android.synthetic.main.placedetails_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

internal class PlaceDetailsFragment : MviFragment<PlaceDetailsIntent, PlaceDetailsState, PlaceDetailsSubscription>(
    initialIntent = PlaceDetailsIntent.LoadData
) {

    override val layoutResource = R.layout.placedetails_fragment

    override val menuResource = R.menu.placedetails_menu


    private val placeId by argumentString(KEY_PLACEID)


    override fun provideViewModel() = getViewModel<PlaceDetailsViewModel> { parametersOf(placeId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placedetails_toolbar.attachToActivity()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.placedetails_delete -> postIntent(PlaceDetailsIntent.DeletePlace)
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    override fun render(state: PlaceDetailsState) {
        val place: Place = state.place
        val imageUrl = place.imageUrl

        placedetails_name.text = place.name
        placedetails_time.text = place.time.format()
        placedetails_image.loadImage(imageUrl)
        placedetails_noimage.isVisible = imageUrl.isNullOrEmpty()

        placedetails_progressbar.isVisible = state.isLoading
    }

    override fun onSubscriptionReceived(subscription: PlaceDetailsSubscription) {
        when (subscription) {
            is PlaceDetailsSubscription.DataLoadingFailed -> toast(
                R.string.placedetails_unexpectederror
            )

            PlaceDetailsSubscription.PlaceDeleted -> toast(
                getString(R.string.placedetails_placedeleted, currentState?.place?.name)
            )
        }

        activity?.onBackPressed()
    }


    internal companion object {

        private const val KEY_PLACEID = "placeId"

        /**
         * Creates a new instance of [PlaceDetailsFragment]
         *
         * @param placeId [Place] identifier
         */
        internal fun newInstance(placeId: String) = PlaceDetailsFragment().withArguments {
            putString(KEY_PLACEID, placeId)
        }

    }

}