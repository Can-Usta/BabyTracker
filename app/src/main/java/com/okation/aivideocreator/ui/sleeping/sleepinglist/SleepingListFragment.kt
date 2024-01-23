package com.okation.aivideocreator.ui.sleeping.sleepinglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.okation.aivideocreator.databinding.FragmentSleepingListBinding
import com.okation.aivideocreator.ui.sleeping.SleepingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SleepingListFragment : Fragment() {
    private lateinit var binding : FragmentSleepingListBinding

    private val viewModel : SleepingViewModel by activityViewModels()
    private lateinit var sleepingAdapter: SleepingListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSleepingListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sleepingAdapter = SleepingListAdapter()

        binding.rvSleep.adapter =sleepingAdapter
        binding.rvSleep.layoutManager = LinearLayoutManager(requireContext())
        viewModel.sleeping.observe(viewLifecycleOwner){
            sleepingAdapter.submitList(it)
        }
    }
}