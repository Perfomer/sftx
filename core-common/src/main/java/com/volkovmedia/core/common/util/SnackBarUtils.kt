package com.volkovmedia.core.common.util

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.volkovmedia.core.common.R

var Snackbar.isVisible: Boolean
    get() = isShown
    set(value) {
        if (value) show()
        else dismiss()
    }

fun View.createSnackbarWithAction(
    message: String,
    actionText: String,
    @ColorRes actionTextColor: Int = R.color.accent,
    length: Int = Snackbar.LENGTH_LONG,
    onActionClick: () -> Unit
) = Snackbar.make(this, message, length)
    .setAction(actionText) { onActionClick.invoke() }
    .setActionTextColor(resources.getColorCompat(actionTextColor))

fun View.createSnackbar(
    @StringRes message: Int,
    length: Int = Snackbar.LENGTH_LONG
) = Snackbar.make(this, message, length)