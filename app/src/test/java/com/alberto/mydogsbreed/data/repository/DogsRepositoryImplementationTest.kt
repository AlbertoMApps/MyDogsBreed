package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.local.dao.DogBreedDao
import com.alberto.mydogsbreed.data.mapper.toDogBreedTable
import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.utils.getBreed
import com.alberto.mydogsbreed.utils.getDog
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
    private lateinit var api: DogsApi

    @Mock
    private lateinit var dao: DogBreedDao
    private lateinit var repositoryService: DogsRepositoryImplementation

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repositoryService =
            DogsRepositoryImplementation(api, dao)
    }

    @Test
    fun `When getDogsList() is successful, then return a list of dogs from the dogs API`() =
        runTest {
            whenever(api.getAllBreeds())
                .thenReturn(getDog())

            val result = repositoryService.getDogsList().toList()
            getDog().breedsMessage?.apply {
                assertEquals(::akita.name, result[1].toString())
            }
        }

    @Test
    fun `When getDogsList() has an http connection error, then return an error`() = runTest {
        whenever(api.getAllBreeds())
            .thenThrow(
                HttpException(
                    Response.error<Any>(403, ResponseBody.create("plain/text".toMediaType(), ""))
                )
            )

        val result = repositoryService.getDogsList().toList()
        assertEquals("HTTP 403 Response.error()", result[1].message)
    }

    @Test
    fun `When getDogsBreed() is successful, then return images of a breed`() = runTest {
        whenever(api.getBreedCollection("Akita"))
            .thenReturn(getBreed())
        whenever(dao.getDogBreed("Akita"))
            .thenReturn(getBreed().toDogBreedTable("Akita"))

        val result = repositoryService.getDogsBreed("Akita").toList()
        assertEquals("Akita", result[0].data?.breed)
        assertEquals(
            "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg",
            result[0].data?.images?.get(0) ?: ""
        )
        assertEquals(
            "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg",
            result[0].data?.images?.get(1) ?: ""
        )
    }

    @Test
    fun `When getDogsBreed() has an http connection error, then return an error`() = runTest {
        whenever(api.getBreedCollection("Akita"))
            .thenThrow(
                HttpException(
                    Response.error<Any>(403, ResponseBody.create("plain/text".toMediaType(), ""))
                )
            )
        val result = repositoryService.getDogsBreed("Akita").toList()
        assertEquals("HTTP 403 Response.error()", result[0].message)
    }

}