package com.alberto.mydogsbreed.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.mydogsbreed.presentation.DogsViewModel

@Composable
fun DogsList(
    viewModel: DogsViewModel = hiltViewModel()
) {
    viewModel.getDogsList()
    viewModel.dogsListState.value.data
}