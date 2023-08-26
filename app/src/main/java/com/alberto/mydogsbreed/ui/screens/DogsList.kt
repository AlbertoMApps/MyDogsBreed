package com.alberto.mydogsbreed.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.mydogsbreed.presentation.DogsViewModel

@Composable
fun DogsList(
    viewModel: DogsViewModel = hiltViewModel()
) {
    viewModel.getDogsList()
    val isLoading = viewModel.dogsListState.value.isLoading
    val dogData = viewModel.dogsListState.value.data
    if (!isLoading) {
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            DogsListScreen(dogData)
        }
    } else {
        CircularProgressIndicator(
            Modifier.size(
                24.dp
            )
        )
    }
}

@Composable
fun DogsListScreen(dogData: List<String>) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = dogData) { dogsName ->
            DogsListItemScreen(
                dogsName.replaceFirstChar { it.uppercase() },
                {})
        }
    }

}