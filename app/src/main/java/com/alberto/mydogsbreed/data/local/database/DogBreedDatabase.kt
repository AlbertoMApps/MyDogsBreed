package com.alberto.mydogsbreed.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alberto.mydogsbreed.data.local.converters.Converters
import com.alberto.mydogsbreed.data.local.dao.DogBreedDao
import com.alberto.mydogsbreed.data.local.model.DogBreedTable

@TypeConverters(Converters::class)
@Database(
    entities = [DogBreedTable::class],
    version = 1,
    exportSchema = false
)
abstract class DogBreedDatabase : RoomDatabase() {
    abstract fun getDogBreedDao(): DogBreedDao

}