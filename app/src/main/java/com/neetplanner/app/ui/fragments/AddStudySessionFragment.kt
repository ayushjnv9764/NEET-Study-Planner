package com.neetplanner.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.neetplanner.app.data.database.NEETStudyDatabase
import com.neetplanner.app.data.entity.StudySession
import com.neetplanner.app.data.repository.StudySessionRepository
import com.neetplanner.app.databinding.FragmentAddStudySessionBinding
import com.neetplanner.app.ui.viewmodel.StudySessionViewModel
import com.neetplanner.app.ui.viewmodel.StudySessionViewModelFactory
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AddStudySessionFragment : Fragment() {
    private var _binding: FragmentAddStudySessionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StudySessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddStudySessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = NEETStudyDatabase.getDatabase(requireContext())
        val repository = StudySessionRepository(database.studySessionDao())
        val factory = StudySessionViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(StudySessionViewModel::class.java)

        setupUI()
    }

    private fun setupUI() {
        binding.btnSaveSession.setOnClickListener {
            saveSession()
        }
        binding.btnCancel.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun saveSession() {
        val subject = binding.spSubject.selectedItem.toString()
        val topic = binding.etTopic.text.toString()
        val duration = binding.etDuration.text.toString().toIntOrNull() ?: 0
        val notes = binding.etNotes.text.toString()
        val date = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        val time = LocalTime.now().format(DateTimeFormatter.ISO_TIME)

        if (topic.isEmpty() || duration <= 0) {
            showError("Please fill all fields correctly")
            return
        }

        val session = StudySession(
            subject = subject,
            topic = topic,
            duration = duration,
            date = date,
            time = time,
            status = "PENDING",
            notes = notes
        )

        viewModel.insertSession(session)
        requireActivity().onBackPressed()
    }

    private fun showError(message: String) {
        // Show error message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
