package com.neetplanner.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.neetplanner.app.data.database.NEETStudyDatabase
import com.neetplanner.app.data.repository.DailyProgressRepository
import com.neetplanner.app.databinding.FragmentProgressBinding
import com.neetplanner.app.ui.viewmodel.ProgressViewModel
import com.neetplanner.app.ui.viewmodel.ProgressViewModelFactory
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ProgressFragment : Fragment() {
    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = NEETStudyDatabase.getDatabase(requireContext())
        val repository = DailyProgressRepository(database.dailyProgressDao())
        val factory = ProgressViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ProgressViewModel::class.java)

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.totalMinutes.collect { minutes ->
                val hours = minutes / 60.0
                binding.tvTotalHoursStudied.text = String.format("%.1f", hours)
            }
        }

        lifecycleScope.launch {
            viewModel.averageDailyMinutes.collect { average ->
                val hours = average / 60.0
                binding.tvAverageDailyHours.text = String.format("%.1f", hours)
            }
        }

        lifecycleScope.launch {
            viewModel.lastWeekProgress.collect { progress ->
                updateProgressChart(progress)
            }
        }
    }

    private fun updateProgressChart(progress: List<com.neetplanner.app.data.entity.DailyProgress>) {
        if (progress.isNotEmpty()) {
            val maxMinutes = progress.maxOfOrNull { it.totalMinutesStudied } ?: 360
            val days = progress.sortedBy { it.date }.takeLast(7)
            
            // Update progress bars
            days.forEachIndexed { index, day ->
                val percentage = ((day.totalMinutesStudied.toFloat() / maxMinutes) * 100).roundToInt()
                when (index) {
                    0 -> binding.progressDay1.progress = percentage
                    1 -> binding.progressDay2.progress = percentage
                    2 -> binding.progressDay3.progress = percentage
                    3 -> binding.progressDay4.progress = percentage
                    4 -> binding.progressDay5.progress = percentage
                    5 -> binding.progressDay6.progress = percentage
                    6 -> binding.progressDay7.progress = percentage
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
