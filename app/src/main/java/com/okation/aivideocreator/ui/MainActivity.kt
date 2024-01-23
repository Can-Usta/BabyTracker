package com.okation.aivideocreator.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.ActivityMainBinding
import com.okation.aivideocreator.ui.inapp.InAppViewModel
import com.okation.aivideocreator.utils.Constants.GOOGLE_API_KEY
import com.revenuecat.purchases.LogLevel
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val viewModel : InAppViewModel by viewModels()

    private val navController : NavController by lazy {
        findNavController(R.id.babyTrackNav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.initializePremiumStatus(applicationContext)

        viewModel.isPremium.observe(this) { isPremium ->
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (isPremium == true && destination.id == R.id.firstOnboardingFragment) {
                    navController.navigate(R.id.action_firstOnboardingFragment_to_homeFragment)
                }
            }
        }

        Purchases.logLevel = LogLevel.DEBUG
        Purchases.configure(PurchasesConfiguration.Builder(this,GOOGLE_API_KEY).build())





    }
}