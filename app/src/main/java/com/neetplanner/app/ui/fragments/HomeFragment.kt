package com.neetplanner.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.neetplanner.app.data.database.NEETStudyDatabase
import com.neetplanner.app.data.repository.StudySessionRepository
import com.neetplanner.app.databinding.FragmentHomeBinding
import com.neetplanner.app.ui.adapter.StudySessionAdapter
import com.neetplanner.app.ui.viewmodel.StudySessionViewModel
import com.neetplanner.app.ui.viewmodel.StudySessionViewModelFactory
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StudySessionViewModel
    private lateinit var adapter: StudySessionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        val database = NEETStudyDatabase.getDatabase(requireContext())
        val repository = StudySessionRepository(database.studySessionDao())
        val factory = StudySessionViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(StudySessionViewModel::class.java)

        setupRecyclerView()
        setupObservers()
        loadTodaysSessions()
    }

    private fun setupRecyclerView() {
        adapter = StudySessionAdapter(emptyList()) { session ->
            // Handle session click
        }
        binding.rvTodaysSessions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.sessions.collect { sessions ->
                adapter.updateSessions(sessions)
                updateStats(sessions)
            }
        }

        lifecycleScope.launch {
            viewModel.totalStudyTime.collect { totalTime ->
                binding.tvTotalHours.text = String.format("%.1f", totalTime / 60.0)
            }
        }
    }

    private fun loadTodaysSessions() {
        val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        viewModel.loadSessionsByDate(today)
    }

    private fun updateStats(sessions: List<StudySession>) {
        val completed = sessions.count { it.status == "COMPLETED" }
        val totalMinutes = sessions.filter { it.status == "COMPLETED" }.sumOf { it.duration }
        binding.tvCompletedSessions.text = completed.toString()
        binding.tvTotalMinutes.text = totalMinutes.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
