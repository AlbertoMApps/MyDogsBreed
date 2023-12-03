package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.getRandomBreedImages
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class DogsRepositoryImplementationTest {

    @Mock
    private lateinit var dogsApiService: DogsApi
    private lateinit var dogsRepository: DogsRepositoryImplementation

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dogsRepository =
            DogsRepositoryImplementation(dogsApiService)
    }

    @Test
    fun `When we get random images, then we need to make sure they exist`() =
        runTest {
            whenever(dogsApiService.getRandomDogsImages())
                .thenReturn(getRandomBreedImages())

            val result = dogsRepository.getRandomDogsImages().toList()
            assertEquals(getRandomBreedImages().images, result[1].data)
        }

    @Test
    fun `When we get random images and an unexpected error occurred, then return a 403 error`() =
        runTest {
            whenever(dogsApiService.getRandomDogsImages())
                .thenThrow(
                    HttpException(
                        Response.error<Any>(
                            403,
                            ResponseBody.create("plain/text".toMediaType(), "")
                        )
                    )
                )

            val result = dogsRepository.getRandomDogsImages().toList()
            assertEquals("HTTP 403 Response.error()", result[1].message)
        }

}