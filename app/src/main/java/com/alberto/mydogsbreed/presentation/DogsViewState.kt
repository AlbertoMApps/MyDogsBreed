package com.alberto.mydogsbreed.presentation

import com.alberto.mydogsbreed.data.remote.model.DogBreed

data class DogsViewState(
    val isLoading: Boolean = false,
    val data: List<String> = listOf(),
    val errorMessage: String = ""
)

data class DogsBreedViewState(
    val isLoading: Boolean = false,
    val data: DogBreed = DogBreed(),
    val errorMessage: String = ""
)