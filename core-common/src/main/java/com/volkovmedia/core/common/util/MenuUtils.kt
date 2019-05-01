package com.volkovmedia.core.common.util

import android.view.Menu
import androidx.annotation.IdRes
import androidx.appcompat.widget.SearchView

fun Menu.findSearchView(@IdRes id: Int): SearchView {
    val searchMenuItem = findItem(id)
    return searchMenuItem?.actionView as SearchView
}