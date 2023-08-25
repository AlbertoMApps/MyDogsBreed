package com.alberto.mydogsbreed.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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
        DogListScreen(dogData)
    } else {
        CircularProgressIndicator(
            Modifier.size(
                24.dp
            )
        )
    }
}

@Composable
fun DogListScreen(dogData: List<String>) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = dogData) {
            Text(text = it)
        }
    }

}