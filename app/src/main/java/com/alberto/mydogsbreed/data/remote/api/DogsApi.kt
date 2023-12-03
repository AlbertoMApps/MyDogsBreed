package com.alberto.mydogsbreed.data.remote.api

import com.alberto.mydogsbreed.data.common.DOGS_API_IMAGES
import com.alberto.mydogsbreed.data.remote.model.DogsImages
import retrofit2.http.GET

interface DogsApi {
    @GET(DOGS_API_IMAGES)
    suspend fun getRandomDogsImages(): DogsImages

}