package com.hiroozawa.ractsil.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hiroozawa.ractsil.data.Car
import com.hiroozawa.ractsil.databinding.CarItemBinding
import com.hiroozawa.ractsil.ui.MainActivityViewModel

/**
 * [RecyclerView.Adapter] that can display a [Car], Has a reference to the [MainActivityViewModel]
 * to send actions back to it.
 */
class CarRecyclerViewAdapter(private val viewModel: MainActivityViewModel) :
    ListAdapter<Car, CarRecyclerViewAdapter.ViewHolder>(CarDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainActivityViewModel, item: Car) {

            binding.viewmodel = viewModel
            binding.car = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CarItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class CarDiffCallBack : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}

