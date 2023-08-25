package com.alberto.mydogsbreed.data.remote.model

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("message")
    val message: Message? = null,
    val status: String? = null
)