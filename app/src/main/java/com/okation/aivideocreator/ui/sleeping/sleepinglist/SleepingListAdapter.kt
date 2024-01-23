package com.okation.aivideocreator.ui.sleeping.sleepinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.data.entities.Sleeping
import com.okation.aivideocreator.databinding.SleepingListItemBinding


class SleepingListAdapter : ListAdapter<Sleeping, SleepingListAdapter.SleepingViewHolder>(SleepingDiffCallback()) {
    class SleepingViewHolder(private val binding : SleepingListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sleeping: Sleeping) {
            binding.tvFellSleep.text = "Fell Sleep: ${sleeping.sleepingTime}"
            binding.tvWokeUp.text = "Woke Up: ${sleeping.wokeUpTime}"
            binding.tvSleepNote.text = "Note: ${sleeping.sleepingNote }"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepingViewHolder {
        val binding = SleepingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SleepingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SleepingViewHolder, position: Int) {
        val currentSleeping =getItem(position)
        holder.bind(currentSleeping)
    }
}
class SleepingDiffCallback : DiffUtil.ItemCallback<Sleeping>() {
    override fun areItemsTheSame(oldItem: Sleeping, newItem: Sleeping): Boolean {
        return oldItem.sleepingId == newItem.sleepingId
    }

    override fun areContentsTheSame(oldItem: Sleeping, newItem: Sleeping): Boolean {
        return oldItem == newItem
    }
}