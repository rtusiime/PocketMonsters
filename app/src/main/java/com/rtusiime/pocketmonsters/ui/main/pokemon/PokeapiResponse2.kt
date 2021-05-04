package com.rtusiime.pocketmonsters.ui.main.pokemon

data class Sprite(val front_default: String, val other: Other)

data class  Other(val dream_world: DreamWorld)

data class DreamWorld(val front_default: String)

class PokeapiResponse2 {
    var height: Int = 0
    var  base_experience: Int = 0
    var weight: Int = 0
    var sprites: Sprite? = null
    var id: Int =0
}
