package com.alberto.mydogsbreed.presentation

data class DogsImagesViewState(
    val isLoading: Boolean = false,
    val data: List<String> = listOf(),
    val errorMessage: String = ""
)