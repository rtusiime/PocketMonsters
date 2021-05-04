package com.rtusiime.pocketmonsters.ui.main.pokemon




class PokeapiResponse {

   var results: ArrayList<Result>? = null

}

data class Result(val name: String, val url: String )