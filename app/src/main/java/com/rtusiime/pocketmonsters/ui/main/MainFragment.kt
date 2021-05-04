package com.rtusiime.pocketmonsters.ui.main

import android.app.AppOpsManager
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.SharedMemory
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.preference.PreferenceManager

import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rtusiime.pocketmonsters.MainActivity.Companion.FONT_SIZE
import com.rtusiime.pocketmonsters.MainActivity.Companion.SET_DARK_THEME
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.MainFragmentBinding
import com.rtusiime.pocketmonsters.ui.main.pokemon.Pokemon

private const val TAG = "MainFragment"
class MainFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {


    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding? = null
    private val pokemonAdapter = PokemonAdapter()
    private val prefs: SharedPreferences by lazy{
        PreferenceManager.getDefaultSharedPreferences(activity)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val bindingMain = MainFragmentBinding.inflate(inflater, container, false)
        prefs.registerOnSharedPreferenceChangeListener(this )
        binding = bindingMain
        binding?.apply {
            floatingActionButton.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_detail)
                sharedViewModel.firstFetch(5)
                Log.d(TAG,"this bitch is working!!!")

            }
            pokemonRecyclerView.run{
                layoutManager = LinearLayoutManager(context)
                adapter = pokemonAdapter
            }
        }
        return bindingMain.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var pokemon: Pokemon
        private val pokemonTextView: TextView = itemView.findViewById(R.id.pokemon_number)

        fun bind(pokemon: Pokemon) {
//            this.pokemon = Pokemon
//            pokemonTextView.text = Pokemon
        }
    }

    private inner class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {
        var pokemons: List<Pokemon> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
            val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
            return PokemonViewHolder(view)
        }

        override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) = holder.bind(pokemon = pokemons[position])

        override fun getItemCount() = pokemons.size

        fun updateWords(newWords: List<Pokemon>) {
            this.pokemons = newWords
            notifyDataSetChanged()
        }

        fun getWordAtPosition(position: Int): Pokemon {
            return pokemons[position]
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key){
            SET_DARK_THEME ->{
                setDarkTheme()
            }
            FONT_SIZE ->{
                setFontSize()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun setFontSize() {
    val metrics = resources.displayMetrics
        when (prefs.getString(FONT_SIZE,"0")?.toInt()){
            0 -> {
                resources.configuration.fontScale = 1f
                metrics.scaledDensity = resources.configuration.fontScale * metrics.density
                context?.createConfigurationContext(resources.configuration)

            }
            1 ->{
                resources.configuration.fontScale = 1.5f
                metrics.scaledDensity = resources.configuration.fontScale * metrics.density
                context?.createConfigurationContext(resources.configuration)
            }
            2 ->{
                resources.configuration.fontScale = 2f
                metrics.scaledDensity = resources.configuration.fontScale * metrics.density
                context?.createConfigurationContext(resources.configuration)
            }
            else ->{
                resources.configuration.fontScale = 1f
                metrics.scaledDensity = resources.configuration.fontScale * metrics.density
                context?.createConfigurationContext(resources.configuration)
            }

        }


    }

    private fun setDarkTheme() {
        context?.setTheme(R.style.ThemeOverlay_AppCompat_Dark)
    }
    companion object{
        const val DEFAULT_POKEMON_URL = "https://pokeapi.co" //https://pokeapi.co/api/v2/ability/{id or name}/
    }                                                                 //https://pokeapi.co/api/v2/pokemon?limit=151

}