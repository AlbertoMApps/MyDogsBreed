package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.local.dao.DogBreedDao
import com.alberto.mydogsbreed.data.mapper.toDogBreed
import com.alberto.mydogsbreed.data.mapper.toDogBreedTable
import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.data.remote.model.BreedsMessage
import com.alberto.mydogsbreed.data.remote.model.DogBreed
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DogsRepositoryImplementation @Inject constructor(
    private val dogsApi: DogsApi,
    private val dogBreedDao: DogBreedDao
) : DogsRepositoryService {

    /**
     * This method will get a list of breeds
     */
    override fun getDogsList(): Flow<Resource<List<String>>> = flow {
//        emit(Resource.Loading())
        try {
            val dogsBreedList = arrayListOf<String>()
            val dogsList = dogsApi.getAllBreeds()
            dogsList.breedsMessage?.apply {
                addDogsBreedList(this, dogsBreedList)
            }
            emit(Resource.Success(data = dogsBreedList))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "The list of dogs could not be loaded"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check your internet connection"))
        }

    }

    /**
     * This method will add a list of breeds returned from the API
     */
    private fun addDogsBreedList(message: BreedsMessage, dogsBreedList: ArrayList<String>) {
        message.apply {
            dogsBreedList.apply {
                add(::affenpinscher.name)
                add(::african.name)
                add(::airedale.name)
                add(::akita.name)
                add(::appenzeller.name)
                add(::australian.name)
                add(::basenji.name)
                add(::beagle.name)
                add(::bluetick.name)
                add(::borzoi.name)
                add(::bouvier.name)
                add(::boxer.name)
                add(::brabancon.name)
                add(::briard.name)
                add(::buhund.name)
                add(::bulldog.name)
                add(::bullterrier.name)
                add(::cattledog.name)
                add(::chihuahua.name)
                add(::chow.name)
                add(::clumber.name)
                add(::cockapoo.name)
                add(::collie.name)
                add(::coonhound.name)
                add(::corgi.name)
                add(::cotondetulear.name)
                add(::dachshund.name)
                add(::dalmatian.name)
                add(::dane.name)
                add(::deerhound.name)
                add(::dhole.name)
                add(::dingo.name)
                add(::doberman.name)
                add(::elkhound.name)
                add(::entlebucher.name)
                add(::eskimo.name)
                add(::finnish.name)
                add(::frise.name)
                add(::germanshepherd.name)
                add(::greyhound.name)
                add(::groenendael.name)
                add(::havanese.name)
                add(::hound.name)
                add(::husky.name)
                add(::keeshond.name)
                add(::kelpie.name)
                add(::komondor.name)
                add(::kuvasz.name)
                add(::labradoodle.name)
                add(::labrador.name)
                add(::leonberg.name)
                add(::lhasa.name)
                add(::malamute.name)
                add(::malinois.name)
                add(::maltese.name)
                add(::mastiff.name)
                add(::mexicanhairless.name)
                add(::mix.name)
                add(::mountain.name)
                add(::newfoundland.name)
                add(::otterhound.name)
                add(::ovcharka.name)
                add(::papillon.name)
                add(::pekinese.name)
                add(::pembroke.name)
                add(::pinscher.name)
                add(::pitbull.name)
                add(::pointer.name)
                add(::pomeranian.name)
                add(::poodle.name)
                add(::pug.name)
                add(::puggle.name)
                add(::pyrenees.name)
                add(::redbone.name)
                add(::retriever.name)
                add(::ridgeback.name)
                add(::rottweiler.name)
                //More list of dogs based on their API to add...
                //The API is bad designed and you need the variables added manually.
            }
        }
    }

    /**
     * This method will get a list of breed's images
     */
    override fun getDogsBreed(dogBreed: String): Flow<Resource<DogBreed>> = flow {
        try {
            var dogBreedTable = dogBreedDao.getDogBreed(dogBreed)
            if (dogBreedTable != null) {
                emit(Resource.Success(data = dogBreedTable.toDogBreed()))
            } else {
                val dogsBreedCollection = dogsApi.getBreedCollection(dogBreed)
                dogBreedDao.deleteDogBreed()
                dogsBreedCollection.toDogBreedTable(dogBreed)
                    ?.let { dogBreedDao.insertDogBreedTable(it) }
                dogBreedTable = dogBreedDao.getDogBreed(dogBreed)
                emit(Resource.Success(data = dogBreedTable?.toDogBreed()))
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "The breed selected could not be loaded"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check your internet connection"))
        }
    }

}