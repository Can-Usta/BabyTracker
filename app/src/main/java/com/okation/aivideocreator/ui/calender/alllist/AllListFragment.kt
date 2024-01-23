package com.okation.aivideocreator.ui.calender.alllist

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.okation.aivideocreator.databinding.FragmentAllListBinding
import com.okation.aivideocreator.ui.feeding.FeedingViewModel
import com.okation.aivideocreator.ui.feeding.feedinglist.FeedingListAdapter
import com.okation.aivideocreator.ui.sleeping.SleepingViewModel
import com.okation.aivideocreator.ui.sleeping.sleepinglist.SleepingListAdapter
import com.okation.aivideocreator.ui.symptom.SymptomsViewModel
import com.okation.aivideocreator.ui.symptom.symptomlist.SymptomListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllListFragment() : Fragment() {
    private lateinit var binding : FragmentAllListBinding

    private lateinit var sleepAdapter : SleepingListAdapter
    private val sleepingViewModel : SleepingViewModel by activityViewModels()

    private lateinit var feedingAdapter: FeedingListAdapter
    private val feedingViewModel : FeedingViewModel by activityViewModels()

    private lateinit var symptomAdapter : SymptomListAdapter
    private val symptomsViewModel : SymptomsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            feedingAdapter = FeedingListAdapter()
            rvFeeding.adapter = feedingAdapter
            rvFeeding.layoutManager = LinearLayoutManager(context)
            feedingViewModel.feeding.observe(viewLifecycleOwner){feed->
                feed.let {
                    feedingAdapter.submitList(feed)
                }
            }

            sleepAdapter = SleepingListAdapter()
            rvSleep.adapter = sleepAdapter
            rvSleep.layoutManager = LinearLayoutManager(context)
            sleepingViewModel.sleeping.observe(viewLifecycleOwner) {sleepingList->
                sleepAdapter.submitList(sleepingList)
            }

            symptomAdapter = SymptomListAdapter()
            rvSymptoms.adapter = symptomAdapter
            rvSymptoms.layoutManager = LinearLayoutManager(context)
            symptomsViewModel.symptoms.observe(viewLifecycleOwner){symptomsList->
                symptomsList.let {
                    symptomAdapter.submitList(it)
                }
            }
        }
    }
}