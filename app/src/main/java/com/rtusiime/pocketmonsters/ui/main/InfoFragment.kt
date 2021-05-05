package com.rtusiime.pocketmonsters.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rtusiime.pocketmonsters.BuildConfig
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.FragmentInfoBinding
import com.rtusiime.pocketmonsters.databinding.FragmentSettingsBinding

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null
private var count = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val bindingInfo = FragmentInfoBinding.inflate(inflater, container, false)
        binding = bindingInfo
        binding?.apply {
            imageView.setOnClickListener{

                if (count==7){
                    count=0
                    findNavController().navigate(R.id.action_infoFragment_to_secretDialog)
                }
                count++
            }
            buildTimeTextView.text = BuildConfig.BUILD_TIME
            titleTextView.text = resources.getString(R.string.app_name)
            versionTextView.text = BuildConfig.VERSION_NAME
            copyrightTextView.text = resources.getString(R.string.copyright)

        }
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