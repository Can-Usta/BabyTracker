package com.okation.aivideocreator.ui.symptom.symptomdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.okation.aivideocreator.databinding.FragmentSymptomsDetailBinding
import com.okation.aivideocreator.ui.symptom.SymptomsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SymptomsDetailFragment : Fragment() {
    private lateinit var binding : FragmentSymptomsDetailBinding
    private val viewModel : SymptomsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSymptomsDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            symptomsBackButton.setOnClickListener {
                findNavController().navigateUp()
                viewModel.clearSymptomsSelection()

            }
            binding.btnSaveSymptomsDetail.setOnClickListener {
                viewModel.symptomsDetail.value.let { selectedSymptom ->
                    viewModel._symptomsDetail.value = selectedSymptom

                }
                findNavController().navigateUp()
            }
            val adapter = SymptomDetailAdapter(this@SymptomsDetailFragment) { viewModel.setSymptomSelectStatus(it) }



            rvSymptoms.layoutManager = GridLayoutManager(requireContext(), 2)
            rvSymptoms.adapter = adapter

            viewModel.symptomsDetail.observe(viewLifecycleOwner){
                adapter.submitList(it)

            }
        }


    }

}