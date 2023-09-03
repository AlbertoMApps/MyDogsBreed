package com.alberto.mydogsbreed.utils

import com.alberto.mydogsbreed.data.remote.model.Dog
import com.alberto.mydogsbreed.data.remote.model.DogBreed

fun getDog() =
    Dog()

fun getBreed() =
    DogBreed(
        "Akita",
        listOf(
            "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg",
            "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg"
        )
    )