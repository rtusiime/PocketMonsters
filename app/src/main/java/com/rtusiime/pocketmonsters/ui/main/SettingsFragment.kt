package com.rtusiime.pocketmonsters.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
   private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingSettings = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = bindingSettings
        binding?.apply {

        }
        return bindingSettings.root
    }


}