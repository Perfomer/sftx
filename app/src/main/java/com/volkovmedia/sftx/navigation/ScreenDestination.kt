package com.volkovmedia.sftx.navigation

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.placelist.DI_FRAGMENT_PLACELIST
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class ScreenDestination : SupportAppScreen(), KoinComponent {

    class PlaceList : ScreenDestination() {
        override fun getFragment() = get<Fragment>(DI_FRAGMENT_PLACELIST)
    }

}