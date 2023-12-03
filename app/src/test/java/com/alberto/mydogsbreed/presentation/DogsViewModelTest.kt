package com.alberto.mydogsbreed.presentation

import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.getRandomBreedImages
import com.alberto.mydogsbreed.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DogsViewModelTest {

    @RelaxedMockK
    private lateinit var dogsRepository: DogsRepositoryService
    private lateinit var dogsViewModel: DogsViewModel

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
    fun `When we try to get dogs images from the dogsRepository, then the view model should be initially loading`() =
        runTest {
            val flow = flow {
                emit(Resource.Loading(data = emptyList<String>()))
            }
            every { dogsRepository.getRandomDogsImages() } returns flow
            dogsViewModel = DogsViewModel(dogsRepository)
            val state = dogsViewModel.dogsState.value
            assertEquals(true, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(emptyList<String>(), state.data)
        }

    @Test
    fun `When we get dogs images, then the view model returns a list of dogs images`() =
        runTest {
            val flow = flow {
                emit(Resource.Success(data = getRandomBreedImages().images))
            }
            every { dogsRepository.getRandomDogsImages() } returns flow
            dogsViewModel = DogsViewModel(dogsRepository)
            val state = dogsViewModel.dogsState.value
            assertEquals(false, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(getRandomBreedImages().images, state.data)
        }

    @Test
    fun `When the repository service has an error loading a list of dogs images, then the view model returns that error`() =
        runTest {
            val flow = flow {
                emit(
                    Resource.Error(
                        message = "Unexpected error loading a list of dogs images",
                        data = emptyList<String>()
                    )
                )
            }
            every { dogsRepository.getRandomDogsImages() } returns flow
            dogsViewModel = DogsViewModel(dogsRepository)
            val state = dogsViewModel.dogsState.value
            assertEquals(false, state.isLoading)
            assertEquals(emptyList<String>(), state.data)
            assertEquals("Unexpected error loading a list of dogs images", state.errorMessage)
        }

}