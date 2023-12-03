package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.remote.api.DogsApi
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

    /**
     * This method will get a list of random dogs images
     */
    override fun getRandomDogsImages(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        try {
            val randomDogsImages = dogsApi.getRandomDogsImages().images
            emit(Resource.Success(data = randomDogsImages))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "The breed's images couldn't be loaded"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check your internet connection"
                )
            )
        }
    }

}