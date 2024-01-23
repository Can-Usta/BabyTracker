package com.okation.aivideocreator.ui.sleeping


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.data.dao.BabyTrackerDao
import com.okation.aivideocreator.data.database.AppDatabase
import com.okation.aivideocreator.databinding.FragmentSleepPageBinding
import com.okation.aivideocreator.utils.SaveButtonVisibility
import com.okation.aivideocreator.utils.SaveLoadingState
import com.okation.aivideocreator.utils.TimePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

@AndroidEntryPoint
class SleepPageFragment : Fragment() {
    private lateinit var binding : FragmentSleepPageBinding
    private lateinit var db : AppDatabase
    private lateinit var sDao : BabyTrackerDao

    private val viewModel : SleepingViewModel  by activityViewModels()

    private val timePicker: TimePicker by lazy { TimePicker(requireContext()) }
    private lateinit var saveButtonVisibility : SaveButtonVisibility

    private lateinit var saveLoadingState: SaveLoadingState

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSleepPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd")
        db = AppDatabase.getDatabase(requireContext())
        sDao = db.babyTrackerDao()


        binding.apply {
            saveButtonVisibility = SaveButtonVisibility(tvFellSleep,tvWokeUp,noteEditText,sleepSaveButton)

            sleepBackButton.setOnClickListener {
                val action = SleepPageFragmentDirections.actionSleepPageFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            fellSleepEditText.setOnClickListener {
                timePicker.showTimePickerDialog(tvFellSleep)
            }
            tvFellSleep.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }
            wokeUpEditText.setOnClickListener{
                timePicker.showTimePickerDialog(tvWokeUp)
            }
            tvWokeUp.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }
            noteEditText.addTextChangedListener {
                saveButtonVisibility.changeSaveButtonVisibility()
            }

            sleepSaveButton.setOnClickListener {
                val fellSleepTime = tvFellSleep.text.toString()
                val wokeUpTime = tvWokeUp.text.toString()
                val sleepingNote = noteEditText.text.toString()
                val date = LocalDateTime.now()
                val formattedDate = date.format(dateTimeFormatter)

                viewModel.saveSleepingToDB(fellSleepTime,wokeUpTime,sleepingNote,formattedDate)

                saveLoadingState = SaveLoadingState(loadingScreen,progressBar,savedTextView,findNavController())
                saveLoadingState.showLoadingState()
            }
        }
    }
}