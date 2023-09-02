package com.alberto.mydogsbreed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.alberto.mydogsbreed.data.local.model.DogBreedTable

@Dao
interface DogBreedDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertDogBreedTable(dogBreed: DogBreedTable)

    @Query("Delete from dogBreedTable")
    suspend fun deleteDogBreed()

    @Query("Select * from dogBreedTable where breed=:breed")
    suspend fun getDogBreed(breed: String): DogBreedTable?

}