package com.rtusiime.pocketmonsters.ui.main.pokemon


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Pokeapi {
    @GET("pokemon")//https://pokeapi.co/api/v2/pokemon?limit=151
    fun fetchPokemon(
            @Query("limit") limit:Int
    ): Call<PokeapiResponse>

    @GET("pokemon/{number}")
    fun fetchPokemon2(
            @Path("number") number: Int
    ): Call<PokeapiResponse2>
}