package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.png.interview.databinding.FragmentForecastBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.adapter.ForecastListAdapter
import com.png.interview.weather.ui.adapter.SpacesItemDecoration
import com.png.interview.weather.ui.binder.ForecastFragmentViewBinder

class ForecastFragment : InjectedFragment() {

    val args: ForecastFragmentArgs by navArgs()
    private lateinit var binding: FragmentForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForecastBinding.inflate(inflater, container, false).apply {
            viewBinder = ForecastFragmentViewBinder(getViewModel(), args.query)
            this.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forecastListAdapter = ForecastListAdapter()
        binding.forecastList.apply {
            adapter = forecastListAdapter
            addItemDecoration(SpacesItemDecoration())
        }
        binding.viewBinder?.forecastViewData?.observe(viewLifecycleOwner) { forecastData ->
            forecastData?.let {
                forecastListAdapter.submitList(it)
            }
        }
    }
}