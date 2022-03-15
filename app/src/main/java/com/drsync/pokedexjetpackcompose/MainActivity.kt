package com.drsync.pokedexjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.drsync.pokedexjetpackcompose.pokemonlist.PokemonListScreen
import com.drsync.pokedexjetpackcompose.ui.theme.PokedexJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexJetpackComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LIST_SCREEN
                ) {
                   composable(LIST_SCREEN) {
                       PokemonListScreen(navController = navController)
                   }
                    composable(
                        "$DETAIL_SCREEN/{$DOMINANT_COLOR}/{$POKEMON_NAME}",
                        arguments = listOf(
                            navArgument(DOMINANT_COLOR) {
                                type = NavType.IntType
                            },
                            navArgument(POKEMON_NAME) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(DOMINANT_COLOR)
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString(POKEMON_NAME)
                        }
                    }
                }
            }
        }
    }
    companion object {
        const val LIST_SCREEN = "pokemon_list_screen"
        const val DETAIL_SCREEN = "pokemon_detail_screen"
        const val DOMINANT_COLOR = "dominantColor"
        const val POKEMON_NAME = "pokemonName"
    }
}