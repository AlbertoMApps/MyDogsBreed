package com.alberto.mydogsbreed.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.alberto.mydogsbreed.data.common.DOGS_API_BASE_URL
import com.alberto.mydogsbreed.data.local.dao.DogBreedDao
import com.alberto.mydogsbreed.data.local.database.DogBreedDatabase
import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.data.repository.DogsRepositoryImplementation
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogsModule {

    @Provides
    @Singleton
    fun provideDogsApi(
        client: OkHttpClient,
        gson: Gson
    ): DogsApi =
        Retrofit.Builder()
            .baseUrl(DOGS_API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DogsApi::class.java)

    @Provides
    @Singleton
    fun getGson(): Gson =
        GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message -> Log.d("Logger", message) }
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideDogsRepositoryImplementation(
        api: DogsApi,
        dao: DogBreedDao
    ): DogsRepositoryService =
        DogsRepositoryImplementation(
            api,
            dao
        )

    @Provides
    fun provideDogBreedDatabase(application: Application): DogBreedDatabase =
        Room.databaseBuilder(
            application,
            DogBreedDatabase::class.java,
            "dog_breed_db"
        ).build()

    @Provides
    fun provideCoinPaprikaDao(db: DogBreedDatabase): DogBreedDao =
        db.getDogBreedDao()

}