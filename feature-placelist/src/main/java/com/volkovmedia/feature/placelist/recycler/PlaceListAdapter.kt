package com.volkovmedia.feature.placelist.recycler

import android.view.View
import com.volkovmedia.core.common.view.recycler.BindableEntityAdapter
import com.volkovmedia.core.data.datasource.database.entity.Place
import com.volkovmedia.feature.placelist.R

internal class PlaceListAdapter(
    private val onClick: (Place) -> Unit
) : BindableEntityAdapter<Place, PlaceViewHolder>() {



    override fun onLayoutRequested(viewType: Int) = R.layout.placelist_item

    override fun onCreateViewHolder(view: View, viewType: Int) = PlaceViewHolder(view, ::onClick)


    private fun onClick(position: Int) = onClick.invoke(items[position])

}