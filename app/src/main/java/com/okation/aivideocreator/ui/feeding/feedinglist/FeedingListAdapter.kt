package com.okation.aivideocreator.ui.feeding.feedinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.data.entities.Feeding
import com.okation.aivideocreator.databinding.FeedingListItemBinding


class FeedingListAdapter : ListAdapter<Feeding,FeedingListAdapter.FeedingViewHolder>(FeedDiffCallBack()) {
    class FeedingViewHolder(private val binding : FeedingListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feeding: Feeding){
            binding.tvAmount.text = "Feed Amount: ${feeding.feedingAmount}"
            binding.tvNote.text = "Note: ${feeding.feedingNote}"
            binding.tvTime.text = "Feed Time: ${feeding.feedingTime}"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedingViewHolder {
        val binding = FeedingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedingViewHolder, position: Int) {
        val currentFeeding = getItem(position)
        holder.bind(currentFeeding)
    }
    class FeedDiffCallBack : DiffUtil.ItemCallback<Feeding>(){
        override fun areItemsTheSame(oldItem: Feeding, newItem: Feeding): Boolean {
            return oldItem.feedingId == newItem.feedingId
        }

        override fun areContentsTheSame(oldItem: Feeding, newItem: Feeding): Boolean {
            return oldItem == newItem
        }
    }
}