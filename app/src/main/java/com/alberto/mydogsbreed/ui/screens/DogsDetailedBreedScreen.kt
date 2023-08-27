package com.alberto.mydogsbreed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alberto.mydogsbreed.presentation.DogsViewModel
import com.alberto.mydogsbreed.ui.theme.MyDogsBreedTheme

@Composable
fun DogsDetailedBreed(
    viewModel: DogsViewModel = hiltViewModel()
) {
    viewModel.getDogsBreed("akita")
    val dogBreedImages = viewModel.dogsBreedState.value.data.images
    DogsDetailedBreedScreen(dogBreedImages)
}

@Composable
fun DogsDetailedBreedScreen(dogBreedImages: ArrayList<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(items = dogBreedImages) { image ->
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(300.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DogsDetailedBreedScreenPreview() {
    MyDogsBreedTheme {
        DogsDetailedBreedScreen(
            dogBreedImages = arrayListOf(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_4037.jpg",
                "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg",
                "https://images.dog.ceo/breeds/hound-blood/n02088466_10831.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_4037.jpg",
                "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg",
                "https://images.dog.ceo/breeds/hound-blood/n02088466_10831.jpg"
            )
        )
    }
}