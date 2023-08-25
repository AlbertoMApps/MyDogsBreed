package com.alberto.mydogsbreed.presentation

data class DogsViewState(
    val isLoading: Boolean = false,
    val data: List<String> = listOf(),
    val errorMessage: String = ""
)