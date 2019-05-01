package com.volkovmedia.core.common.view.recycler.base

import android.view.View

abstract class BindableViewHolder<T>(
    containerView: View
) : BaseViewHolder(containerView) {

    abstract fun bind(item: T)

}