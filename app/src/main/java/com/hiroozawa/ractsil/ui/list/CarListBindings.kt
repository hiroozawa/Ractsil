package com.hiroozawa.ractsil.ui.list

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.domain.Car

/**
 * [BindingAdapter]s for the [Car]s list.
 */

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<CarUiModel>) {
    (listView.adapter as CarRecyclerViewAdapter).submitList(items)
}


@BindingAdapter("app:image")
fun setImage(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        error(R.drawable.ic_fallback)
    }
}