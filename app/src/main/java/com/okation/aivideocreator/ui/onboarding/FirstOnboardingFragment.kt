package com.okation.aivideocreator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.okation.aivideocreator.databinding.FragmentFirstOnboardingBinding


class FirstOnboardingFragment : Fragment() {
    private lateinit var binding: FragmentFirstOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstOnboardingBinding.inflate(inflater,container,false)

        binding.firstOnboardingButton.setOnClickListener {
            val action = FirstOnboardingFragmentDirections.actionFirstOnboardingFragmentToSecondOnboardingFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }


}