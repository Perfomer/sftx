package com.volkovmedia.core.common.util

import java.text.SimpleDateFormat
import java.util.*

val currentDate: Date
    get() = Calendar.getInstance().time

fun Date.format(format: String = "dd-MM-yy"): String {
    return format.toDateFormat().format(this)
}

fun String.parseDate(format: String): Date {
    return format.toDateFormat().parse(this)
}

private fun String.toDateFormat(): SimpleDateFormat {
    return SimpleDateFormat(this, Locale.getDefault())
}