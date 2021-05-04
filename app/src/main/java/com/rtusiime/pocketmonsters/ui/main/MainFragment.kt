package com.rtusiime.pocketmonsters.ui.main
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rtusiime.pocketmonsters.MainActivity.Companion.FONT_SIZE
import com.rtusiime.pocketmonsters.MainActivity.Companion.SET_DARK_THEME
import com.rtusiime.pocketmonsters.R
import com.rtusiime.pocketmonsters.databinding.MainFragmentBinding
import com.rtusiime.pocketmonsters.ui.model.Pokemon
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.*
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation


private const val TAG = "MainFragment"

class MainFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {


//    public val recyclerView: RecyclerView = nu
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding? = null
    private val pokemonAdapter = PokemonAdapter()
    private val prefs: SharedPreferences by lazy{
        PreferenceManager.getDefaultSharedPreferences(activity)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        sharedViewModel.firstFetch(5)
        val bindingMain = MainFragmentBinding.inflate(inflater, container, false)
        prefs.registerOnSharedPreferenceChangeListener(this )
        binding = bindingMain
        binding?.apply {
            floatingActionButton.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_detail)
//
            }
            this.pokemonRecyclerView.run{
                Log.d(TAG,"this bitch is working!!!")
                layoutManager = LinearLayoutManager(context)
                adapter = pokemonAdapter
                recyclerView = this
            }
        }
        return bindingMain.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.pokemons.observe(viewLifecycleOwner,{
            pokemonAdapter.updatePokemon(it)
        })

//        val itemTouchHelperCallback =
//                object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//                    override fun onMove(
//                            recyclerView: RecyclerView,
//                            viewHolder: RecyclerView.ViewHolder,
//                            target: RecyclerView.ViewHolder
//                    ) = false
//
//                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                        val thisWord = wordAdapter.getWordAtPosition(viewHolder.adapterPosition)
////                    context?.toast("Deleted: ${thisWord.title}")
//                        itemDeletedAlert(thisWord)
//                        sharedViewModel.deleteWord(word = thisWord)
//                    }
//                }
//
//        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
//        itemTouchHelper.attachToRecyclerView(binding?.wordsRecyclerview)

    }
//    private fun setImage() {
//
//        val effect = when (prefs.getString(EFFECT_SELECTION, "0")?.toInt()) {
//            0 -> CropSquareTransformation()
//            1 -> CropCircleTransformation()
//            2 -> BlurTransformation(context, 15, 1)
//            3 -> PixelationFilterTransformation(context, 48.0f)
//            4 -> InvertFilterTransformation(context)
//            else -> RoundedCornersTransformation(85, 32)
//        }
//
//        val resID = if (prefs.getBoolean(
//                        SHOW_NOW_IMAGE,
//                        false
//                )
//        ) R.drawable.ur_rushrhees_new else R.drawable.ur_rushrhees_old
//
//        val picasso = Picasso.get()
//        picasso.load(resID)
//                .transform(effect)
//                .into(urImageView)
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }


    private inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var pokemon: Pokemon
        private val pokemonTextView: TextView = itemView.findViewById(R.id.pokemon_number)
        private val pokemonImageView: ImageView=  itemView.findViewById(R.id.pokemon__ImageView)


        fun bind(pokemon: Pokemon) {
            Log.d(TAG, "Love me plz 🥺🥺🥺🥺")
            this.pokemon = pokemon
            pokemonTextView.text = pokemon.id.toString()
            val picasso = Picasso.get()
            picasso.load(pokemon.image)
                    .placeholder(R.drawable.pokemon_logo_removebg_preview)
                    .transform(GrayscaleTransformation())
                    .into(pokemonImageView)


        }
    }

    private inner class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {
        var pokedex: List<Pokemon> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
            Log.d(TAG,"this bitch is working!!! nkjwebfbwregfbrwkjgvrvwrbvjlwnvnslkvnklvnlksvlksdnvlksd")
            val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
            Log.d(TAG,"olinga badokwaya")
            return PokemonViewHolder(view)
        }

        override fun onBindViewHolder(holder: PokemonViewHolder, position: Int){
            holder.bind(pokemon = pokedex[position])
        }

        override fun getItemCount() = pokedex.size

       fun updatePokemon(newPokemons: List<Pokemon>) {
            this.pokedex= newPokemons
            notifyDataSetChanged()
        }

        fun getPokemonAtPosition(position: Int): Pokemon {
            return pokedex[position]
        }
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
        var recyclerView: RecyclerView? = null
        const val DEFAULT_POKEMON_URL = "https://pokeapi.co" //https://pokeapi.co/api/v2/ability/{id or name}/
    }                                                                 //https://pokeapi.co/api/v2/pokemon?limit=151

}