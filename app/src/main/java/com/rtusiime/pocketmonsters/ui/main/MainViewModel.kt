package com.rtusiime.pocketmonsters.ui.main


import android.app.Application
import android.util.Log

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rtusiime.pocketmonsters.ui.main.pokemon.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MainViewModel"
class MainViewModel(app: Application) : AndroidViewModel(app) {

    var resultsArrayList: ArrayList<Result>? = null
    private val _name = MutableLiveData<String>()
    var name: LiveData<String> = _name

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url


    private val _height = MutableLiveData<Int>()
    var height: LiveData<Int> = _height
    val heightArrayList = arrayListOf<Int>()

    private val _baseExperience = MutableLiveData<Int>()
    var baseExperience: LiveData<Int> = _baseExperience
    val baseExperienceArrayList = arrayListOf<Int>()

    private val _sprite = MutableLiveData<String>()
    var sprite: LiveData<String> = _sprite
    val spriteArrayList = arrayListOf<Sprite?>()

    private val _weight = MutableLiveData<Int>()
    var weight: LiveData<Int> = _weight
    val weightArrayList = arrayListOf<Int>()

    fun firstFetch(limit: Int) {

        val pokeapiRequest: Call<PokeapiResponse> = pokeapi.fetchPokemon(limit)
        pokeapiRequest.enqueue(object : Callback<PokeapiResponse> {
            override fun onResponse(call: Call<PokeapiResponse>, response: Response<PokeapiResponse>) {
                Log.d(TAG, "printing some shittttt ${response.body()!!.results}")
                response.body()?.let {
                    resultsArrayList = it.results
                }

//                for (i in 1..5) {
//                    SecondFetch(i)
//                }

            }

            override fun onFailure(call: Call<PokeapiResponse>, t: Throwable) {
                Log.d(TAG, "Failed to get response.")
            }
        })
    }


    fun SecondFetch(number: Int){
        val pokeapiRequest2: Call<PokeapiResponse2> = pokeapi.fetchPokemon2(number)
        pokeapiRequest2.enqueue(object : Callback<PokeapiResponse2> {
            override fun onResponse(call: Call<PokeapiResponse2>, response: Response<PokeapiResponse2>) {

                Log.d(TAG, "height is ${response!!.body()} and yo momma is ${response.body()?.sprites} ")
                response.body()?.let {
                    heightArrayList.add(it.height)
                    baseExperienceArrayList.add(it.base_experience)
                    weightArrayList.add(it.weight)
                    spriteArrayList.add(it.sprites)
                }


            }

            override fun onFailure(call: Call<PokeapiResponse2>, t: Throwable) {
                Log.d(TAG, "Failed to get response.")
            }
        })
    }
companion object{
    val pokeapi: Pokeapi by lazy{
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/") //https://pokeapi.co/api/v2/pokemon?limit=151
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return@lazy retrofit.create(Pokeapi::class.java)
    }
}
}