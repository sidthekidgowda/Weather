package com.png.interview.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.ForecastListItemBinding
import com.png.interview.weather.ui.model.ForecastViewData

class ForecastListAdapter : ListAdapter<ForecastViewData, ForecastListAdapter.ForecastViewHolder>(
    object : DiffUtil.ItemCallback<ForecastViewData>() {
        override fun areItemsTheSame(
            oldItem: ForecastViewData,
            newItem: ForecastViewData
        ): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(
            oldItem: ForecastViewData,
            newItem: ForecastViewData
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            ForecastListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ForecastViewHolder(private val binding: ForecastListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastData: ForecastViewData) {
            binding.date = forecastData.date
            binding.maxTemp = forecastData.maxTempF
            binding.minTemp = forecastData.minTempF
            binding.windSpeed = forecastData.windSpeedMPH
            binding.condition = forecastData.condition
        }
    }
}