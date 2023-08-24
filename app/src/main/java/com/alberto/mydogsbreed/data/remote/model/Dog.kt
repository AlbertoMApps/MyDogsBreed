package com.alberto.mydogsbreed.data.remote.model

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("message")
    private val dogsInfo: DogsInfo? = null,
    private val status: String? = null
)