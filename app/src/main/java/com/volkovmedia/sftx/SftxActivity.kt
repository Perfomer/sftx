package com.volkovmedia.sftx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.volkovmedia.feature.placelist.PlaceListNavigator
import com.volkovmedia.sftx.navigation.ScreenDestination
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class SftxActivity : AppCompatActivity(), PlaceListNavigator {

    private val navigatorHolder by inject<NavigatorHolder>()

    private val navigator by inject<SupportAppNavigator> { parametersOf(this, R.id.main_frame) }

    private val router by inject<Router>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sftx_activity)

        if (savedInstanceState == null) {
            router.newRootScreen(ScreenDestination.PlaceList)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        router.exit()
    }


    override fun navigateToPlaceDetails(id: String) = router.navigateTo(ScreenDestination.PlaceDetails(id))

}