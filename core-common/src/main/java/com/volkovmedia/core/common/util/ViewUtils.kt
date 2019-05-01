package com.volkovmedia.core.common.util

import android.content.Context
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

var View.onClick: () -> Unit
    get() = {}
    set(value) = setOnClickListener { value() }

var View.onLongClick: () -> Unit
    get() = {}
    set(value) = setOnLongClickListener {
        value()
        true
    }

fun Fragment.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View? {
    return context?.inflate(resource, root, attachToRoot)
}

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    val inflater = LayoutInflater.from(this)
    return inflater.inflate(resource, root, attachToRoot)
}

fun View.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return context.inflate(resource, root, attachToRoot)
}

fun View.setBackgroundTint(@ColorRes colorRes: Int) {
    backgroundTintList = resources.getColorStateList(colorRes)
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    imageTintList = resources.getColorStateList(colorRes)
}

fun RecyclerView.init(
    adapter: RecyclerView.Adapter<*>,
    itemTouchHelper: ItemTouchHelper.Callback? = null,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
) {
    this.layoutManager = layoutManager
    this.adapter = adapter

    itemTouchHelper?.let { this.addItemTouchHelper(it) }
}

fun RecyclerView.addItemTouchHelper(callback: ItemTouchHelper.Callback) {
    ItemTouchHelper(callback).attachToRecyclerView(this)
}