package com.hiroozawa.ractsil.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hiroozawa.ractsil.data.Car

/**
 * [BindingAdapter]s for the [Car]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Car>) {
    (listView.adapter as CarRecyclerViewAdapter).submitList(items)
}