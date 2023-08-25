package com.alberto.mydogsbreed.presentation

import com.alberto.mydogsbreed.data.remote.model.Dog

data class DogsViewState(
    val isLoading: Boolean = false,
    val data: Dog = Dog(),
    val errorMessage: String = ""
)