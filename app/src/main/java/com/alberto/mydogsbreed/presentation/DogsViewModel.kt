package com.alberto.mydogsbreed.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.mydogsbreed.data.remote.model.DogBreed
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
    private val _dogsListState = mutableStateOf(DogsViewState())
    val dogsListState = _dogsListState

    private var dogsBreedJob: Job? = null
    private val _dogsBreedState = mutableStateOf(DogsBreedViewState())
    val dogsBreedState = _dogsBreedState

    init {
        getDogsList()
    }

    fun getDogsList() {
        dogsJob?.cancel()
        dogsJob = viewModelScope.launch {
            dogsRepositoryService.getDogsList().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _dogsListState.value = DogsViewState(
                            isLoading = true,
                            data = result.data ?: listOf()
                        )
                    }

                    is Resource.Success -> {
                        _dogsListState.value = DogsViewState(
                            data = result.data ?: listOf()
                        )
                    }

                    is Resource.Error -> {
                        _dogsListState.value = DogsViewState(
                            errorMessage = result.message
                                ?: "Unexpected error loading a dog's breed list"
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun getDogsBreed(dogsBreed: String) {
        dogsBreedJob?.cancel()
        dogsBreedJob = viewModelScope.launch {
            dogsRepositoryService.getDogsBreed(dogsBreed).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _dogsBreedState.value = DogsBreedViewState(
                            isLoading = true,
                            data = result.data ?: DogBreed()
                        )
                    }

                    is Resource.Success -> {
                        _dogsBreedState.value = DogsBreedViewState(
                            data = result.data ?: DogBreed()
                        )
                    }

                    is Resource.Error -> {
                        _dogsBreedState.value = DogsBreedViewState(
                            errorMessage = result.message
                                ?: "Unexpected error loading a dog's breed"
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}