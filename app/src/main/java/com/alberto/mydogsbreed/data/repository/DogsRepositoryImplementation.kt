package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.extension.addDogsBreedList
import com.alberto.mydogsbreed.data.local.dao.DogBreedDao
import com.alberto.mydogsbreed.data.mapper.toDogBreed
import com.alberto.mydogsbreed.data.mapper.toDogBreedTable
import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.data.remote.model.DogBreed
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DogsRepositoryImplementation @Inject constructor(
    private val dogsApi: DogsApi,
    private val dogBreedDao: DogBreedDao
) : DogsRepositoryService {

    /**
     * This method will get a list of breeds
     */
    override fun getDogsList(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        try {
            val dogsBreedList = arrayListOf<String>()
            val dogsList = dogsApi.getAllBreeds()
            dogsList.breedsMessage?.addDogsBreedList(dogsBreedList)
            emit(Resource.Success(data = dogsBreedList))

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

    /**
     * This method will get a list of breed's images
     */
    override fun getDogsBreed(dogBreed: String): Flow<Resource<DogBreed>> = flow {
        try {
            var dogBreedTable = dogBreedDao.getDogBreed(dogBreed)
            if (dogBreedTable != null) {
                emit(Resource.Success(data = dogBreedTable.toDogBreed()))
            } else {
                val dogsBreedCollection = dogsApi.getBreedCollection(dogBreed)
                dogBreedDao.deleteDogBreed()
                dogsBreedCollection.toDogBreedTable(dogBreed)
                    ?.let { dogBreedDao.insertDogBreedTable(it) }
                dogBreedTable = dogBreedDao.getDogBreed(dogBreed)
                emit(Resource.Success(data = dogBreedTable?.toDogBreed()))
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "The breed selected could not be loaded"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check your internet connection"))
        }
    }

}