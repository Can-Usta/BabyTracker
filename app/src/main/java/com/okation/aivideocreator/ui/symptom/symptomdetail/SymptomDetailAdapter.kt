package com.okation.aivideocreator.ui.symptom.symptomdetail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.model.SymptomsDetail
import com.okation.aivideocreator.databinding.SymptomDetailItemBinding


class SymptomDetailAdapter(private val context: SymptomsDetailFragment, private val onItemClicked: (SymptomsDetail) -> Unit,) : ListAdapter<SymptomsDetail, SymptomDetailAdapter.SymptomsViewHolder>(SymptomsDiffCallback()) {
    inner class SymptomsViewHolder(private val binding: SymptomDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SymptomsDetail) {
            binding.imgSymptom.setImageResource(item.imageSymptom)
            binding.tvSymptomsName.text = context.resources.getString(item.nameSymptom)

            binding.cardView.isChecked = item.isSelected

            if (binding.cardView.isChecked){
                binding.cardView.setStrokeColor(R.drawable.gradient_color)
            }else{
                binding.cardView.setStrokeColor(Color.parseColor("#00000000"))
            }

            binding.root.setOnClickListener {
                binding.cardView.isChecked = item.isSelected
                onItemClicked(item)
            }

        }

    }

    class SymptomsDiffCallback : DiffUtil.ItemCallback<SymptomsDetail>() {
        override fun areItemsTheSame(oldItem: SymptomsDetail, newItem: SymptomsDetail): Boolean {
            return oldItem.nameSymptom == newItem.nameSymptom
        }

        override fun areContentsTheSame(oldItem: SymptomsDetail, newItem: SymptomsDetail): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomsViewHolder {
        val binding = SymptomDetailItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SymptomsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        val symptom = getItem(position)
        holder.bind(symptom)

    }
}