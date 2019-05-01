package com.volkovmedia.core.common.view.recycler

import com.volkovmedia.core.common.KeyEntity
import com.volkovmedia.core.common.view.recycler.base.BindableViewHolder

abstract class BindableEntityAdapter<T : KeyEntity<*>, VH : BindableViewHolder<T>> : EntityAdapter<T, VH>() {

    override fun onBindViewHolder(holder: VH, item: T) = holder.bind(item)

}