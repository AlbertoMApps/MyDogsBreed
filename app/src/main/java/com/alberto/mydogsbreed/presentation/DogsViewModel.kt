package com.alberto.mydogsbreed.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val dogsRepositoryService: DogsRepositoryService
) : ViewModel() {

    private var dogsJob: Job? = null
    private val _dogsState = mutableStateOf(DogsImagesViewState())
    val dogsState = _dogsState

    init {
        getRandomDogsImages()
    }

    fun getRandomDogsImages() {
        dogsJob?.cancel()
        dogsJob = viewModelScope.launch {
            dogsRepositoryService.getRandomDogsImages().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _dogsState.value = DogsImagesViewState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _dogsState.value = DogsImagesViewState(
                            data = result.data ?: listOf()
                        )
                    }

                    else -> {
                        _dogsState.value = DogsImagesViewState(
                            errorMessage = result.message
                                ?: "Unexpected error loading random dogs images"
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}