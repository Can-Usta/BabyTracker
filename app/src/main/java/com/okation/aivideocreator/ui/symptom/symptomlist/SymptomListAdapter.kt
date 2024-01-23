package com.okation.aivideocreator.ui.symptom.symptomlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.data.entities.Symptoms
import com.okation.aivideocreator.databinding.SymptomListItemBinding


class SymptomListAdapter : ListAdapter<Symptoms, SymptomListAdapter.SymptomListViewHolder>(SymptomDiffCallback()) {
    class SymptomListViewHolder(val binding : SymptomListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(symptoms: Symptoms) {
            binding.tvSymptoms.text = "${symptoms.symptomName}"
            binding.tvNote.text = "Note: ${symptoms.symptomsNote}"
            binding.tvTime.text = "Symptom Time${symptoms.symptomTime}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomListViewHolder {
        val binding = SymptomListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SymptomListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SymptomListViewHolder, position: Int) {
        val currentSymptom = getItem(position)
        holder.bind(currentSymptom)
    }
}

class SymptomDiffCallback : DiffUtil.ItemCallback<Symptoms>() {
    override fun areItemsTheSame(oldItem: Symptoms, newItem: Symptoms): Boolean {
        return oldItem.symptomId == newItem.symptomId
    }

    override fun areContentsTheSame(oldItem: Symptoms, newItem: Symptoms): Boolean {
        return oldItem == newItem
    }
}


