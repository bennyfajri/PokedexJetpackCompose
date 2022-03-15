package com.drsync.pokedexjetpackcompose.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraMoon: UltraSunUltraMoon
)