package com.png.interview.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.AutocompleteListItemBinding
import com.png.interview.weather.ui.handler.AutocompleteHandler
import com.png.interview.weather.ui.model.AutocompleteViewData

class AutocompleteListAdapter(
    private val handler: AutocompleteHandler
) : ListAdapter<AutocompleteViewData, AutocompleteListAdapter.AutocompleteViewHolder>(
    object : DiffUtil.ItemCallback<AutocompleteViewData>() {
        override fun areItemsTheSame(
            oldItem: AutocompleteViewData,
            newItem: AutocompleteViewData
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: AutocompleteViewData,
            newItem: AutocompleteViewData
        ): Boolean {
            return oldItem == newItem
        }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutocompleteViewHolder {
        return AutocompleteViewHolder(
            AutocompleteListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AutocompleteViewHolder, position: Int) {
        holder.bind(getItem(position), handler)
    }

    class AutocompleteViewHolder(
        private val binding: AutocompleteListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            autocompleteViewData: AutocompleteViewData,
            handler: AutocompleteHandler
        ) {
            binding.handler = handler
            binding.location = autocompleteViewData.name
        }
    }
}