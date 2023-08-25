package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.data.remote.model.Dog
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DogsRepositoryImplementation @Inject constructor(
    private val dogsApi: DogsApi
) : DogsRepositoryService {

    override fun getDogsList(): Flow<Resource<Dog>> = flow {
        emit(Resource.Loading())
        try {
            val dogsList = dogsApi.getAllBreeds()
            emit(Resource.Success(data = dogsList))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "The list of dogs could not be loaded"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check your internet connection"))
        }

    }

}