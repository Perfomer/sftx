package com.volkovmedia.feature.placelist.recycler

import android.view.View
import androidx.core.view.isVisible
import com.volkovmedia.core.common.util.format
import com.volkovmedia.core.common.util.loadImage
import com.volkovmedia.core.common.util.onClick
import com.volkovmedia.core.common.view.recycler.base.BindableViewHolder
import com.volkovmedia.core.data.datasource.database.entity.Place
import kotlinx.android.synthetic.main.placelist_item.*

internal class PlaceViewHolder(
    containerView: View,
    private val onClick: (Int) -> Unit
) : BindableViewHolder<Place>(containerView) {

    init {
        placelist_item_card.onClick = { if (hasPosition) onClick.invoke(adapterPosition) }
    }

    override fun bind(item: Place) {
        placelist_item_name.text = item.name
        placelist_item_time.text = item.time.format()

        placelist_item_image.loadImage(item.imageUrl)
        placelist_item_noimage.isVisible = item.imageUrl.isNullOrBlank()
    }

}