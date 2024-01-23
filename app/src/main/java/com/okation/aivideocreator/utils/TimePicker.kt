package com.okation.aivideocreator.utils

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimePicker(private val context : Context) {
    private val calender = Calendar.getInstance()


    fun showTimePickerDialog(timeTextView: TextView) {
        val timePickerDialog = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                calender.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calender.set(Calendar.MINUTE, minute)
                updateTimeTextView(timeTextView)
            },
            calender.get(Calendar.HOUR_OF_DAY),
            calender.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    fun updateTimeTextView(timeTextView: TextView) {
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val formattedTime = timeFormat.format(calender.time)
        timeTextView.text = formattedTime
    }
}