package com.alberto.mydogsbreed.data.mapper

import com.alberto.mydogsbreed.data.local.model.DogBreedTable
import com.alberto.mydogsbreed.data.remote.model.DogBreed

fun DogBreed.toDogBreedTable(dogsBreed: String) =
    images?.let { DogBreedTable(breed = dogsBreed, images = it) }

fun DogBreedTable.toDogBreed() =
    DogBreed(breed = breed, images = images)