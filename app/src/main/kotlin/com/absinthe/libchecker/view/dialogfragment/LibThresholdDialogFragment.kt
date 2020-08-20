package com.absinthe.libchecker.view.dialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.absinthe.libchecker.R
import com.absinthe.libchecker.constant.Constants
import com.absinthe.libchecker.constant.GlobalValues
import com.absinthe.libchecker.databinding.LayoutLibRefThresholdBinding
import com.absinthe.libchecker.utils.SPUtils
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.analytics.EventProperties

class LibThresholdDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = LayoutLibRefThresholdBinding.inflate(layoutInflater)
        binding.slider.value = GlobalValues.libReferenceThreshold.value?.toFloat() ?: 1f

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle(R.string.lib_ref_threshold)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val threshold = binding.slider.value.toInt()
                GlobalValues.libReferenceThreshold.value = threshold
                SPUtils.putInt(Constants.PREF_LIB_REF_THRESHOLD, threshold)
                Analytics.trackEvent(Constants.Event.SETTINGS, EventProperties().set("PREF_LIB_REF_THRESHOLD", threshold.toLong()))
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
    }
}