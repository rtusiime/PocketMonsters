package com.rtusiime.pocketmonsters.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceFragmentCompat
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.FragmentSettingsBinding

class SettingsFragment : PreferenceFragmentCompat() {


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setHasOptionsMenu(true)
        setPreferencesFromResource(R.xml.preferences_fragment,rootKey)
    }


}