package com.okation.aivideocreator.ui.symptom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.databinding.FragmentSymptomsPageBinding
import com.okation.aivideocreator.utils.SaveButtonVisibility
import com.okation.aivideocreator.utils.SaveLoadingState
import com.okation.aivideocreator.utils.TimePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class SymptomsPageFragment : Fragment() {
    private lateinit var binding: FragmentSymptomsPageBinding
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd")

    private val viewModel: SymptomsViewModel by activityViewModels()
    private val timePicker: TimePicker by lazy { TimePicker(requireContext()) }

    private lateinit var saveButtonVisibility: SaveButtonVisibility

    private lateinit var saveLoadingState: SaveLoadingState


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSymptomsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            saveButtonVisibility = SaveButtonVisibility(tvSymptomsTime,tvSymptoms,noteEditText,symptomsSaveButton)
            symptomsBackButton.setOnClickListener {
                val action = SymptomsPageFragmentDirections.actionSymptomsPageFragmentToHomeFragment()
                findNavController().navigate(action)
                viewModel.clearSymptomsSelection()
            }

            vSymptoms.setOnClickListener {
                val action = SymptomsPageFragmentDirections.actionSymptomsPageFragmentToSymptomsDetailFragment()
                findNavController().navigate(action)
            }
            viewModel.symptomsDetail.observe(viewLifecycleOwner) { symptoms ->
                val selectedSymptoms = symptoms.filter {
                    it.isSelected
                }

                tvSymptoms.text = selectedSymptoms.joinToString(", ") {
                    requireContext().getString(it.nameSymptom)
                }
            }
            tvSymptomsTime.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }
            tvSymptoms.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }
            noteEditText.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }


            symptomsSaveButton.setOnClickListener {
                val time = tvSymptomsTime.text.toString()
                val symptoms = tvSymptoms.text.toString()
                val note = noteEditText.text.toString()
                val date = LocalDateTime.now()
                val formattedDate = date.format(dateTimeFormatter)


                viewModel.saveSymptomsToDb(time, symptoms, note,formattedDate)
                viewModel.clearSymptomsSelection()

                saveLoadingState = SaveLoadingState(loadingScreen,progressBar,savedTextView,findNavController())
                saveLoadingState.showLoadingState()
            }

            symptomTimeEditText.setOnClickListener {
                timePicker.showTimePickerDialog(tvSymptomsTime)
            }


        }

    }

}