package com.alberto.mydogsbreed.data.remote.model

import com.google.gson.annotations.SerializedName

data class DogsImages(
    @SerializedName("message")
    val images: List<String>? = null,
    val status: String? = null

)