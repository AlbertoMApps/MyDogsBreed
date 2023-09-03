package com.alberto.mydogsbreed.presentation

import com.alberto.mydogsbreed.data.remote.model.DogBreed
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DogsViewModelTest {

    @RelaxedMockK
    private lateinit var repositoryService: DogsRepositoryService
    private lateinit var viewModel: DogsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When the repository service is loading, then the view model should be loading`() =
        runTest {
            val flow = flow {
                emit(Resource.Loading(data = emptyList<String>()))
            }
            every { repositoryService.getDogsList() } returns flow
            viewModel = DogsViewModel(repositoryService)
            val state = viewModel.dogsListState.value
            assertEquals(true, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(emptyList<String>(), state.data)
        }

    @Test
    fun `When the repository service is successful, then the view model returns a list of breeds`() =
        runTest {
            val flow = flow {
                emit(Resource.Success(data = listOf("Akita", "Dalmatian")))
            }
            every { repositoryService.getDogsList() } returns flow
            viewModel = DogsViewModel(repositoryService)
            val state = viewModel.dogsListState.value
            assertEquals(false, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(listOf("Akita", "Dalmatian"), state.data)
        }

    @Test
    fun `When the repository service has an error loading a list of dogs, then the view model returns that error`() =
        runTest {
            val flow = flow {
                emit(
                    Resource.Error(
                        message = "Unexpected error loading a list of dogs",
                        data = emptyList<String>()
                    )
                )
            }
            every { repositoryService.getDogsList() } returns flow
            viewModel = DogsViewModel(repositoryService)
            val state = viewModel.dogsListState.value
            assertEquals(false, state.isLoading)
            assertEquals("Unexpected error loading a list of dogs", state.errorMessage)
        }

    @Test
    fun `When the repository service is successful, then the view model returns a dog's breed with a list of images`() =
        runTest {
            val flow = flow {
                emit(Resource.Success(data = DogBreed()))
            }
            every { repositoryService.getDogsBreed("Akita") } returns flow
            viewModel = DogsViewModel(repositoryService)
            val state = viewModel.dogsBreedState.value
            assertEquals(false, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(DogBreed(), state.data)
        }

}