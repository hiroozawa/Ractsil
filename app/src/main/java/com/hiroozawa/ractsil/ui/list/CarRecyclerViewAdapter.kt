package com.hiroozawa.ractsil.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hiroozawa.ractsil.databinding.CarItemBinding
import com.hiroozawa.ractsil.ui.model.CarUiModel

/**
 * [RecyclerView.Adapter] that can display a [CarUiModel], Has a reference to the [CarListViewModel]
 * to send actions back to it.
 */
class CarRecyclerViewAdapter(private val viewModel: CarListViewModel) :
    ListAdapter<CarUiModel, CarRecyclerViewAdapter.ViewHolder>(CarDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: CarListViewModel, item: CarUiModel) {

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

class CarDiffCallBack : DiffUtil.ItemCallback<CarUiModel>() {
    override fun areItemsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
        return oldItem == newItem
    }
}

