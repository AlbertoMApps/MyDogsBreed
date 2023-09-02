package com.alberto.mydogsbreed.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogBreedTable(
    @PrimaryKey
    val breed: String,
    val images: List<String>

)