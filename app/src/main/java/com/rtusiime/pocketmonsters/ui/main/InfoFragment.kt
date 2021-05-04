package com.rtusiime.pocketmonsters.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.FragmentInfoBinding
import com.rtusiime.pocketmonsters.databinding.FragmentSettingsBinding

class InfoFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentInfoBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val bindingInfo = FragmentInfoBinding.inflate(inflater, container, false)
        binding = bindingInfo
        return bindingInfo.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}