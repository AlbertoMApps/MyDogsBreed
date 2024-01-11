package com.alberto.mydogsbreed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val dogsRepositoryService: DogsRepositoryService
) : ViewModel() {

    private var dogsJob: Job? = null
    private val _dogsState = MutableStateFlow(DogsImagesViewState())
    val dogsState = _dogsState.asStateFlow()

    init {
        getRandomDogsImages()
    }

    fun getRandomDogsImages() {
        dogsJob?.cancel()//This is to cancel any asynchronous calls that come back in the future.
        dogsJob = viewModelScope.launch {
            dogsRepositoryService.getRandomDogsImages().collect { result ->
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
            }
        }
    }

}