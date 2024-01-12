package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogsRepositoryImplementation @Inject constructor(
    private val dogsApi: DogsApi
) : DogsRepositoryService {

    /**
     * This method will get a list of random dogs images
     */
    override fun getRandomDogsImages(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        val randomDogsImages = dogsApi.getRandomDogsImages().images
        emit(Resource.Success(data = randomDogsImages))
    }.catch {
        emit(
            Resource.Error(
                message = it.localizedMessage ?: "Unexpected error loading random dogs images"
            )
        )
    }

}