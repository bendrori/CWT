package com.ben.conversions.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ben.conversions.data.local.entities.ConversionData
import com.ben.conversions.databinding.ConversionItemBinding

class HomeListAdapter : ListAdapter<ConversionData, HomeListAdapter.ItemViewHolder>(object :
    DiffUtil.ItemCallback<ConversionData>() {
    override fun areItemsTheSame(oldItem: ConversionData, newItem: ConversionData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ConversionData, newItem: ConversionData): Boolean {
        return oldItem == newItem
    }
}
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            ConversionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val binding: ConversionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(conversionData: ConversionData) {
            binding.apply {
                data = conversionData
                executePendingBindings()
            }
        }
    }


}