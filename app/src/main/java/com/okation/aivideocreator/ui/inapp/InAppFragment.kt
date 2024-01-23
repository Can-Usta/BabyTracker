package com.okation.aivideocreator.ui.inapp

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentInAppBinding
import com.okation.aivideocreator.utils.Constants.IS_PREMIUM
import com.okation.aivideocreator.utils.Constants.PREMIUM
import com.okation.aivideocreator.utils.Constants.SMALL
import com.revenuecat.purchases.Package
import com.revenuecat.purchases.PurchaseParams
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.getOfferingsWith
import com.revenuecat.purchases.purchaseWith
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InAppFragment : Fragment() {
    private lateinit var binding : FragmentInAppBinding

    private lateinit var getPackage : Package

    private val viewModel : InAppViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInAppBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonEnabled(false)

        binding.apply {
            closeImageView.setOnClickListener {
                val action = InAppFragmentDirections.actionInAppFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            val privacySpannableString = SpannableString(getString(R.string.in_app_privacy_policy_text))
            privacySpannableString.setSpan(UnderlineSpan(), 0, privacySpannableString.length, 0)
            tvPrivacy.text = privacySpannableString

            val restoreSpannableString = SpannableString(getString(R.string.in_app_restore_purchase_text))
            restoreSpannableString.setSpan(UnderlineSpan(), 0, restoreSpannableString.length, 0)
            tvRestore.text = restoreSpannableString

            val termsSpannableString = SpannableString(getString(R.string.in_app_term_of_user))
            termsSpannableString.setSpan(UnderlineSpan(), 0, termsSpannableString.length, 0)
            tvTerms.text = termsSpannableString
        }
        Purchases.sharedInstance.getOfferingsWith({
        }) { offerings ->
            offerings.current?.availablePackages?.forEach {
                println(it)
            }
            offerings.current?.getPackage(SMALL)?.also {
                binding.priceText.text = it.product.price.formatted
            }

            Purchases.sharedInstance.getOfferingsWith({
            }) { offerings ->
                binding.premiumButton.setOnClickListener {
                    binding.premiumButton.isChecked = true
                    binding.premiumStartButton.run {
                        isClickable = true
                        isActivated = true
                        isEnabled = true
                    }
                    offerings.current?.getPackage(SMALL)?.also {
                        getPackage = it
                    }
                }
            }

            binding.premiumStartButton.setOnClickListener {
                setButtonEnabled(false)
                Purchases.sharedInstance.purchaseWith(
                    PurchaseParams.Builder(requireActivity(), getPackage).build(),
                    onError = { error, _ ->
                        Log.e("errorCode: ${error.code}", error.message)
                        setButtonEnabled(true)
                    },
                    onSuccess = { _, _ ->
                        viewModel.setPremiumStatus(true)
                        Log.d("Purchase", "Purchase successful")

                        val sharedPreferences = requireContext().getSharedPreferences(PREMIUM, Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putBoolean(IS_PREMIUM, true)
                        editor.apply()
                        findNavController().navigate(R.id.action_inAppFragment_to_homeFragment)
                    }
                )
            }
        }
    }
    private fun setButtonEnabled(enabled: Boolean) {
        binding.premiumStartButton.apply {
            isClickable = enabled
            isActivated = enabled
            isEnabled = enabled
        }
    }

}