package com.okation.aivideocreator.ui.symptom.symptomlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.okation.aivideocreator.databinding.FragmentSymptomListBinding
import com.okation.aivideocreator.ui.symptom.SymptomsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SymptomListFragment : Fragment() {
    private lateinit var binding : FragmentSymptomListBinding

    private val viewModel : SymptomsViewModel by activityViewModels()
    private lateinit var symptomAdapter : SymptomListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSymptomListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        symptomAdapter = SymptomListAdapter()
        binding.rvSymptoms.adapter = symptomAdapter
        binding.rvSymptoms.layoutManager = LinearLayoutManager(requireContext())





        viewModel.symptoms.observe(viewLifecycleOwner){
            symptomAdapter.submitList(it)
        }

    }

}