package com.alberto.mydogsbreed.data.remote.model

import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName("message")
    val images: ArrayList<String> = arrayListOf(),
    val status: String? = null
)