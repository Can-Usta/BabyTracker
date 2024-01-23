package com.okation.aivideocreator.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            homeSleepingButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSleepPageFragment()
                findNavController().navigate(action)
            }
            homeFeedingButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToFeedingPageFragment()
                findNavController().navigate(action)
            }
            settingsImageButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
                findNavController().navigate(action)
            }

            calenderImageButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCalenderFragment()
                findNavController().navigate(action)
            }

            homeSymptomsButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSymptomsPageFragment()
                findNavController().navigate(action)
            }





        }

    }

}