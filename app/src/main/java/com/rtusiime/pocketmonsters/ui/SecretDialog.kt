package com.rtusiime.pocketmonsters.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.DialogueFragmentBinding
import com.rtusiime.pocketmonsters.databinding.FragmentInfoBinding
import com.rtusiime.pocketmonsters.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation

class SecretDialog : BottomSheetDialogFragment(){


    private var binding: DialogueFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogFragment = DialogueFragmentBinding.inflate(inflater,container,false  )
        binding = dialogFragment

        binding?.apply{

            val picasso = Picasso.get()
            picasso.load("https://uploads7.wikiart.org/images/leonardo-da-vinci/mona-lisa.jpg")
                .placeholder(R.drawable.pokemon_splash_screen_removebg_preview)
                .into(imageView3)

            okButton.setOnClickListener{
                dismiss()
            }
            okButton.setOnClickListener{
                dismiss()
            }
        }

        return dialogFragment.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }
}