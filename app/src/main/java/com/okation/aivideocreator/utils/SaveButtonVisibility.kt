package com.okation.aivideocreator.utils

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SaveButtonVisibility(private val firstInput : TextView, private val secondInput : TextView, private val thirdInput : TextView,private val saveButton : Button) {

    fun changeSaveButtonVisibility(){
        if (firstInput.text.isNotEmpty() && secondInput.text.isNotEmpty() && thirdInput.text.isNotEmpty()){
            saveButton.visibility = View.VISIBLE
        }else{
            saveButton.visibility = View.GONE
        }
    }
}