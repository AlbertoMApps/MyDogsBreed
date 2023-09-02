package com.alberto.mydogsbreed.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alberto.mydogsbreed.presentation.DogsViewModel
import com.alberto.mydogsbreed.ui.theme.MyDogsBreedTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun DogsList(
    viewModel: DogsViewModel = hiltViewModel(),
    navigation: NavHostController
) {
    val isLoading = viewModel.dogsListState.value.isLoading
    val dogsData = viewModel.dogsListState.value.data
    val errorMessage = viewModel.dogsListState.value.errorMessage
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::getDogsList
    ) {
        AnimatedVisibility(
            visible = !swipeRefreshState.isRefreshing,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            DogsListScreen(dogsData, navigation)
        }
    }
    if (errorMessage.isNotEmpty()) {
        ErrorLabel(errorMessage = errorMessage)
    }
}

@Composable
fun DogsListScreen(dogsData: List<String>, navigation: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = dogsData) { dogsName ->
            DogsBreedScreen(
                dogsName.replaceFirstChar { it.uppercase() }
            ) {
                navigation.navigate(Screen.DogsDetailedBreed.route.plus("/${dogsName}"))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DogsListScreenPreview() {
    MyDogsBreedTheme {
        DogsListScreen(
            dogsData = listOf(
                "akita", "husky", "beagle"
            ),
            navigation = rememberNavController()
        )
    }
}