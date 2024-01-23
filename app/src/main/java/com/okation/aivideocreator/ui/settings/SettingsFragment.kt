package com.okation.aivideocreator.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.databinding.FragmentSettingsBinding
import com.okation.aivideocreator.ui.inapp.InAppViewModel


class SettingsFragment : Fragment() {
    private lateinit var binding : FragmentSettingsBinding

    private val viewModel : InAppViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            settingsBackButton.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            btnPrivacy.setOnClickListener {
                browsePage()
                premiumButton.isVisible=false
            }
            btnTerms.setOnClickListener {
                browsePage()
                premiumButton.isVisible=false
            }
            btnContact.setOnClickListener {
                sendMail()
            }
            btnRate.setOnClickListener {
                browsePage()
                premiumButton.isVisible=false
            }
            btnRestore.setOnClickListener{
                browsePage()
                premiumButton.isVisible=false
            }

            premiumButton.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToInAppFragment()
                findNavController().navigate(action)
            }

            viewModel.isPremium.observe(viewLifecycleOwner){isPremium->
                premiumButton.isVisible = !isPremium
            }
        }
    }

    private fun browsePage(){
        val webView: WebView = binding.webView
        webView.isVisible = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://neonapps.co/")
    }
    private fun sendMail() {
        val recipientEmail = "support@neonapps.co"
        val subject = "About Baby Tracker App"

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:$recipientEmail")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        } catch (_: android.content.ActivityNotFoundException) {
        }
    }
}