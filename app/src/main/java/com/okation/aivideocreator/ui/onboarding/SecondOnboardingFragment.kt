package com.okation.aivideocreator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.databinding.FragmentSecondOnboardingBinding


class SecondOnboardingFragment : Fragment() {
    private lateinit var binding : FragmentSecondOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondOnboardingBinding.inflate(inflater,container,false)

        binding.secondOnboardingButton.setOnClickListener {
            val action = SecondOnboardingFragmentDirections.actionSecondOnboardingFragmentToInAppFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

}