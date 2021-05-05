package com.rtusiime.pocketmonsters.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.FragmentDetailBinding
import com.rtusiime.pocketmonsters.databinding.FragmentInfoBinding
import com.rtusiime.pocketmonsters.databinding.MainFragmentBinding
import com.rtusiime.pocketmonsters.ui.model.Pokemon
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation


class Detail : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
   private var binding: FragmentDetailBinding? =null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val bindingDetail = FragmentDetailBinding.inflate(inflater, container, false)
        binding = bindingDetail
        binding?.apply {
            val picasso = Picasso.get()
            val pokemon = sharedViewModel.clickedPokemon
            picasso.load(pokemon.image)
                .placeholder(R.drawable.pokemon_logo_removebg_preview)
                .transform(GrayscaleTransformation())
                .into(pokemonDetailImageView)


            pokemonNameTextView.text = pokemon.name
            pokemonNumberTextView.text = pokemon.id.toString()
            pokemonTypeTextView.text = pokemon.base_experience.toString()
            pokemonWeightTextView.text = pokemon.weight.toString()
        }
        return bindingDetail.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}