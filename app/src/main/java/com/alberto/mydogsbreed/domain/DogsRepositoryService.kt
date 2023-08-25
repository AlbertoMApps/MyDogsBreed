package com.alberto.mydogsbreed.domain

import com.alberto.mydogsbreed.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DogsRepositoryService {
    fun getDogsList(): Flow<Resource<List<String>>>

}