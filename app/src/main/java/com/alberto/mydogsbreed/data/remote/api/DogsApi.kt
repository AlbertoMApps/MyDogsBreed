package com.alberto.mydogsbreed.data.remote.api

import com.alberto.mydogsbreed.data.common.DOGS_API_ALL_BREEDS
import com.alberto.mydogsbreed.data.common.DOGS_API_BREED_COLLECTION
import com.alberto.mydogsbreed.data.remote.model.Dog
import com.alberto.mydogsbreed.data.remote.model.DogBreed
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {
    @GET(DOGS_API_ALL_BREEDS)
    suspend fun getAllBreeds(): Dog

    @GET(DOGS_API_BREED_COLLECTION)
    suspend fun getBreedCollection(@Path("breed") breed: String): DogBreed

}