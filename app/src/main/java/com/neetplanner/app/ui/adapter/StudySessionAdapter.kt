package com.neetplanner.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neetplanner.app.data.entity.StudySession
import com.neetplanner.app.databinding.ItemStudySessionBinding

class StudySessionAdapter(
    private var sessions: List<StudySession>,
    private val onSessionClick: (StudySession) -> Unit
) : RecyclerView.Adapter<StudySessionAdapter.SessionViewHolder>() {

    inner class SessionViewHolder(private val binding: ItemStudySessionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(session: StudySession) {
            binding.apply {
                tvSubject.text = session.subject
                tvTopic.text = session.topic
                tvDuration.text = "${session.duration} mins"
                tvDate.text = session.date
                tvTime.text = session.time
                tvStatus.text = session.status
                
                // Set status color
                when (session.status) {
                    "COMPLETED" -> tvStatus.setTextColor(android.graphics.Color.GREEN)
                    "IN_PROGRESS" -> tvStatus.setTextColor(android.graphics.Color.BLUE)
                    else -> tvStatus.setTextColor(android.graphics.Color.GRAY)
                }
                
                root.setOnClickListener {
                    onSessionClick(session)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val binding = ItemStudySessionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SessionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bind(sessions[position])
    }

    override fun getItemCount(): Int = sessions.size

    fun updateSessions(newSessions: List<StudySession>) {
        sessions = newSessions
        notifyDataSetChanged()
    }
}
