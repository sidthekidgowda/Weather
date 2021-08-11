package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.png.interview.databinding.FragmentCurrentWeatherBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.adapter.AutocompleteListAdapter
import com.png.interview.weather.ui.binder.CurrentWeatherFragmentViewBinder
import com.png.interview.weather.ui.isImperial
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import java.lang.IllegalStateException

class CurrentWeatherFragment : InjectedFragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding
    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = getViewModel()
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container,false).apply {
            viewBinder = CurrentWeatherFragmentViewBinder(
                viewModel,
                requireActivity(),
                settingsAction = {
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToSettingsFragment())
                },
                forecastAction = { query ->
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToForecastFragment(query))
                },
            )
            isImperial = sharedPreferences.isImperial()
            this.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val autocompleteListAdapter = AutocompleteListAdapter(
            viewBinder = binding.viewBinder
                ?: throw IllegalStateException("ViewBinder cannot be null")
        )
        binding.viewAutocompleteList.autocompleteList.adapter = autocompleteListAdapter
        binding.etInput.doOnTextChanged { text, start, before, count ->
            if (text.toString().length == 0) return@doOnTextChanged
            viewModel.autoCompleteListSearch(text.toString())
        }
        binding.viewBinder?.autocompleteListData?.observe(viewLifecycleOwner) { locations ->
            locations?.let {
                autocompleteListAdapter.submitList(locations)
            }
        }
    }
}