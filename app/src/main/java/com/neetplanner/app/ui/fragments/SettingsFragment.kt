package com.neetplanner.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neetplanner.app.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSettingsUI()
    }

    private fun setupSettingsUI() {
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            // Handle notifications toggle
        }
        binding.sliderDailyGoal.setOnChangeListener { _, value, _ ->
            binding.tvDailyGoalValue.text = "${value.toInt()} hours"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
