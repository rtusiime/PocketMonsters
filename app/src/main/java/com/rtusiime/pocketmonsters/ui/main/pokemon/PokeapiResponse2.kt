package com.rtusiime.pocketmonsters.ui.main.pokemon


class Pokemon2{

}

data class Sprite(val front_default: String)

class PokeapiResponse2 {
    var height: Int = 0
    var  base_experience: Int = 0
    var weight: Int = 0
    var sprites: Sprite? = null
}

class Pokedex: ArrayList<Result>()


//class PokedexList(jsonObject: JSONObject){
//    var pokes = ArrayList<Result>()
//    init {
//        val gson = Gson()
//        pokes = gson.fromJson(jsonObject,Pokedex::class.java)
//    }
//}