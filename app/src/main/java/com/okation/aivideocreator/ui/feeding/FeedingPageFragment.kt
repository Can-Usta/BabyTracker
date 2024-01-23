package com.okation.aivideocreator.ui.feeding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.databinding.FragmentFeedingPageBinding
import com.okation.aivideocreator.utils.SaveButtonVisibility
import com.okation.aivideocreator.utils.SaveLoadingState
import com.okation.aivideocreator.utils.TimePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class FeedingPageFragment : Fragment() {
    private lateinit var binding: FragmentFeedingPageBinding
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd")

    private val viewModel : FeedingViewModel by activityViewModels()
    private val timePicker: TimePicker by lazy { TimePicker(requireContext()) }
    private lateinit var saveButtonVisibility : SaveButtonVisibility

    private lateinit var saveLoadingState: SaveLoadingState

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            saveButtonVisibility = SaveButtonVisibility(tvFeedingTime,feedingAmountEditText,feedingnoteEditText,feedingSaveButton)
            feedingBackButton.setOnClickListener {
                val action = FeedingPageFragmentDirections.actionFeedingPageFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            tvFeedingTime.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }
            feedingAmountEditText.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }
            feedingnoteEditText.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }

            addSuffixToEditText()

            feedingSaveButton.setOnClickListener {
                val feedingTime = tvFeedingTime.text.toString()
                val feedingAmount = feedingAmountEditText.text.toString()
                val feedingNote = feedingnoteEditText.text.toString()
                val date = LocalDateTime.now()
                val formattedDate = date.format(dateTimeFormatter)

                viewModel.saveFeedingToDB(feedingTime,feedingAmount,feedingNote,formattedDate)
                saveLoadingState = SaveLoadingState(loadingScreen,progressBar,savedTextView,findNavController())
                saveLoadingState.showLoadingState()
            }
            feedingTimeEditText.setOnClickListener{
                timePicker.showTimePickerDialog(tvFeedingTime)
            }
        }
    }

    private fun addSuffixToEditText() {
        binding.apply {
            feedingAmountEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val text = feedingAmountEditText.text.toString()
                    if (!text.endsWith("ml")) {
                        feedingAmountEditText.setText("$text ml")
                    }
                }
            }
        }
    }
}