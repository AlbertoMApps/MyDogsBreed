package com.alberto.mydogsbreed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alberto.mydogsbreed.presentation.DogsViewModel
import com.alberto.mydogsbreed.ui.theme.MyDogsTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

const val tagDogsImagesGridScreen = "TAG_DOGS_IMAGES_GRID_SCREEN"

@Composable
fun DogsScreen(
    viewModel: DogsViewModel = hiltViewModel()
) {
    val dogsViewState = viewModel.dogsState.collectAsState().value
    dogsViewState.copy(
        isLoading = false,
        data = listOf(),
        errorMessage = ""
    )// This copy here won't update the state as source of truth since the state will keep inmutable
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = dogsViewState.isLoading)

    when {
        dogsViewState.data.isNotEmpty() -> {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.getRandomDogsImages() }
            ) {
                DogsContentScreen(dogsViewState.data)
            }
        }

        dogsViewState.errorMessage.isNotEmpty() -> {
            ErrorLabel(errorMessage = dogsViewState.errorMessage)
        }

    }

}

@Composable
fun DogsContentScreen(dogsImages: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 16.dp),
        modifier = Modifier.testTag(tagDogsImagesGridScreen)
    ) {
        items(items = dogsImages) { image ->
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
private fun DogsContentScreenPreview() {
    MyDogsTheme {
        DogsContentScreen(
            dogsImages = listOf(
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