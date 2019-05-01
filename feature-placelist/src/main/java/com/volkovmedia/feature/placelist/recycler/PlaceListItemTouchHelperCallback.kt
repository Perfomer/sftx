package com.volkovmedia.feature.placelist.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.volkovmedia.core.data.datasource.database.entity.Place

internal class PlaceListItemTouchHelperCallback(
    private val adapter: PlaceListAdapter,
    private val onSwipe: (Place) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipe.invoke(adapter.items[viewHolder.adapterPosition])
    }

}