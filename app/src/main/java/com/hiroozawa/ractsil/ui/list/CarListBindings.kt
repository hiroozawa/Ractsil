package com.hiroozawa.ractsil.ui.list

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.model.CarUiModel

/**
 * [BindingAdapter]s for the [Car]s list.
 */

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<CarUiModel>) {
    (listView.adapter as CarRecyclerViewAdapter).submitList(items)
}


@BindingAdapter("image")
fun setImage(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        error(R.drawable.ic_fallback)
    }
}