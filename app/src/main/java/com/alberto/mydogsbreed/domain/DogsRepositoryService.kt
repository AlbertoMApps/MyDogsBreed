package com.alberto.mydogsbreed.domain

import com.alberto.mydogsbreed.data.remote.model.DogsImages
import com.alberto.mydogsbreed.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DogsRepositoryService {
    fun getRandomDogsImages(): Flow<Resource<List<String>>>

}