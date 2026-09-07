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
import com.neetplanner.app.databinding.FragmentScheduleBinding
import com.neetplanner.app.ui.adapter.StudySessionAdapter
import com.neetplanner.app.ui.viewmodel.StudySessionViewModel
import com.neetplanner.app.ui.viewmodel.StudySessionViewModelFactory
import kotlinx.coroutines.launch

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StudySessionViewModel
    private lateinit var adapter: StudySessionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = NEETStudyDatabase.getDatabase(requireContext())
        val repository = StudySessionRepository(database.studySessionDao())
        val factory = StudySessionViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(StudySessionViewModel::class.java)

        setupRecyclerView()
        setupObservers()
        setupFilterButtons()
    }

    private fun setupRecyclerView() {
        adapter = StudySessionAdapter(emptyList()) { session ->
            // Handle session click
        }
        binding.rvSessions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ScheduleFragment.adapter
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.sessions.collect { sessions ->
                adapter.updateSessions(sessions)
            }
        }
    }

    private fun setupFilterButtons() {
        binding.btnPhysics.setOnClickListener {
            viewModel.loadSessionsBySubject("Physics")
        }
        binding.btnChemistry.setOnClickListener {
            viewModel.loadSessionsBySubject("Chemistry")
        }
        binding.btnBiology.setOnClickListener {
            viewModel.loadSessionsBySubject("Biology")
        }
        binding.btnAll.setOnClickListener {
            viewModel.loadAllSessions()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
