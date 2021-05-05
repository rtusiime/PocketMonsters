package com.rtusiime.pocketmonsters.ui.main


import android.app.Application
import android.util.Log

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rtusiime.pocketmonsters.ui.main.MainFragment.Companion.recyclerView
import com.rtusiime.pocketmonsters.ui.main.pokemon.*
import com.rtusiime.pocketmonsters.ui.model.Pokemon

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MainViewModel"

class MainViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var clickedPokemon : Pokemon
    val pokemonList: ArrayList<Pokemon> = arrayListOf()
    private val _pokemons = MutableLiveData<List<Pokemon>>()

    var pokemons: LiveData<List<Pokemon>> = _pokemons

    var resultsArrayList: ArrayList<Result>? = null


    fun firstFetch(limit: Int) {
        val pokeapiRequest: Call<PokeapiResponse> = pokeapi.fetchPokemon(limit)
        pokeapiRequest.enqueue(object : Callback<PokeapiResponse> {
            override fun onResponse(call: Call<PokeapiResponse>, response: Response<PokeapiResponse>) {
                response.body()?.let {
                    resultsArrayList = it.results
                }
                populateModel()
            }

            override fun onFailure(call: Call<PokeapiResponse>, t: Throwable) {
                Log.d(TAG, "Failed to get response.")
            }
        })
    }

    fun populateModel() {

        for (i in 1..resultsArrayList!!.size) {
            val pokemon = Pokemon(resultsArrayList!![i - 1].name, 0, 0, 0, "", 0)
            secondFetch(i, pokemon)

        }

    }

    fun secondFetch(number: Int, pokemon: Pokemon) {
        val pokeapiRequest2: Call<PokeapiResponse2> = pokeapi.fetchPokemon2(number)
        pokeapiRequest2.enqueue(object : Callback<PokeapiResponse2> {
            override fun onResponse(call: Call<PokeapiResponse2>, response: Response<PokeapiResponse2>) {
//                Log.d(TAG, "height is ${response!!.body()} and yo momma is ${response.body()?.sprites} ")
                response.body()?.let {
                    pokemon.height = it.height
                    pokemon.base_experience = it.base_experience
                    pokemon.weight = it.weight
                    pokemon.image = "https://pokeres.bastionbot.org/images/pokemon/${it.id}.png"
                    pokemon.id = it.id
                }

                pokemonList.add(pokemon)
                _pokemons.value = pokemonList
                recyclerView!!.adapter!!.notifyDataSetChanged()


            }

            override fun onFailure(call: Call<PokeapiResponse2>, t: Throwable) {
                Log.d(TAG, "Failed to get response.")
            }
        })
    }

    fun clickedPokemon(pokemon: Pokemon) {
        clickedPokemon = pokemon
    }

    companion object {
        val pokeapi: Pokeapi by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/") //https://pokeapi.co/api/v2/pokemon?limit=151
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return@lazy retrofit.create(Pokeapi::class.java)
        }
    }
}