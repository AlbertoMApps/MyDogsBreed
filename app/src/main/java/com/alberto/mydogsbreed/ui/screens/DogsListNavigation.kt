package com.alberto.mydogsbreed.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val DOGS_BREED_ARG = "dogsBreed"

@Composable
fun DogsListNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.DogsList.route
    ) {
        composable(route = Screen.DogsList.route) {
            DogsList(navigation = navController)
        }
        composable(
            route = Screen.DogsDetailedBreed.route
                .plus("/{$DOGS_BREED_ARG}"),
            arguments = getCoinArguments(DOGS_BREED_ARG)
        ) {
            it.arguments?.getString(DOGS_BREED_ARG)?.let {
                if (it.isNotEmpty()) {
                    DogsDetailedBreed(
                        dogBreed = it
                    )
                }
            }
        }
    }
}

private fun getCoinArguments(argumentName: String): List<NamedNavArgument> =
    listOf(
        navArgument(argumentName) {
            type = NavType.StringType
        }// we could add more arguments here if needed.
    )

sealed class Screen(val route: String) {
    object DogsList : Screen(route = "dogs_list")
    object DogsDetailedBreed : Screen(route = "dogs_detailed_breed")
}

