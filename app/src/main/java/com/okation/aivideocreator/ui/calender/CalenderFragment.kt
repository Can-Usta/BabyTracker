package com.okation.aivideocreator.ui.calender

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentCalenderBinding
import com.okation.aivideocreator.ui.feeding.FeedingViewModel
import com.okation.aivideocreator.ui.sleeping.SleepingViewModel
import com.okation.aivideocreator.ui.symptom.SymptomsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class CalenderFragment : Fragment() {
    private lateinit var binding : FragmentCalenderBinding
    private val calendar = Calendar.getInstance()

    private val feedingViewModel: FeedingViewModel by activityViewModels ()
    private val sleepViewModel: SleepingViewModel by activityViewModels ()
    private val symptomsViewModel: SymptomsViewModel by activityViewModels ()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCalenderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateDate()

        binding.apply {
            calenderBackButton.setOnClickListener {
                val action = CalenderFragmentDirections.actionCalenderFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            tvDateTitle.setOnClickListener {
                showDatePickerDialog()
            }
        }
        val adapter = CalenderAdapter(this)
        binding.viewPager.adapter = adapter

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All"
                1 -> tab.icon= ContextCompat.getDrawable(tabLayout.context, R.drawable.icon_feeding_selected)
                2 -> tab.icon= ContextCompat.getDrawable(tabLayout.context, R.drawable.icon_sleep_selected)
                3 -> tab.icon= ContextCompat.getDrawable(tabLayout.context, R.drawable.icon_symptons_selected)
            }
        }.attach()
    }
    private fun updateDate(){
        val dateFormat = SimpleDateFormat("E, MMM dd", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        binding.tvDateTitle.text = formattedDate

        feedingViewModel.setSelectedDate(formattedDate)
        sleepViewModel.setSelectedDate(formattedDate)
        symptomsViewModel.setSelectedDate(formattedDate)
    }
    private fun showDatePickerDialog() {

        val today = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)

                updateDate()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = today.timeInMillis
        datePickerDialog.show()
    }
}

