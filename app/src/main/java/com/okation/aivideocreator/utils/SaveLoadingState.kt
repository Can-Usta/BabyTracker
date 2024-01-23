package com.okation.aivideocreator.utils

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveLoadingState(private val loading : View,private val progressBar : View, private val savedText : View, private val navController: NavController) {

    fun showLoadingState() {
        progressBar.visibility = View.VISIBLE
        loading.visibility = View.VISIBLE


        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)

            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                savedText.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    navController.navigateUp()
                }, 500)
            }
        }
    }
}