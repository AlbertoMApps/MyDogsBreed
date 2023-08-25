package com.alberto.mydogsbreed.data.repository

import com.alberto.mydogsbreed.data.remote.api.DogsApi
import com.alberto.mydogsbreed.data.remote.model.Message
import com.alberto.mydogsbreed.domain.DogsRepositoryService
import com.alberto.mydogsbreed.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DogsRepositoryImplementation @Inject constructor(
    private val dogsApi: DogsApi
) : DogsRepositoryService {

    /**
     * This method will get a list of breeds
     */
    override fun getDogsList(): Flow<Resource<List<String>>> = flow {
//        emit(Resource.Loading())
        try {
            val dogsBreedList = arrayListOf<String>()
            val dogsList = dogsApi.getAllBreeds()
            dogsList.message?.apply {
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
    private fun addDogsBreedList(message: Message, dogsBreedList: ArrayList<String>) {
        message.apply {
            dogsBreedList.apply {
                add(affenpinscher.toString())
                add(african.toString())
                add(airedale.toString())
                add(akita.toString())
                add(appenzeller.toString())
                add(australian.toString())
                add(basenji.toString())
                add(beagle.toString())
                add(bluetick.toString())
                add(borzoi.toString())
                add(bouvier.toString())
                add(boxer.toString())
                add(brabancon.toString())
                add(briard.toString())
                add(buhund.toString())
                add(bulldog.toString())
                add(bullterrier.toString())
                add(cattledog.toString())
                add(chihuahua.toString())
                add(chow.toString())
                add(clumber.toString())
                add(cockapoo.toString())
                add(collie.toString())
                add(coonhound.toString())
                add(corgi.toString())
                add(cotondetulear.toString())
                add(dachshund.toString())
                add(dalmatian.toString())
                add(dane.toString())
                add(deerhound.toString())
                add(dhole.toString())
                add(dingo.toString())
                add(doberman.toString())
                add(elkhound.toString())
                add(entlebucher.toString())
                add(eskimo.toString())
                add(finnish.toString())
                add(frise.toString())
                add(germanshepherd.toString())
                add(greyhound.toString())
                add(groenendael.toString())
                add(havanese.toString())
                add(hound.toString())
                add(husky.toString())
                add(keeshond.toString())
                add(kelpie.toString())
                add(komondor.toString())
                add(kuvasz.toString())
                add(labradoodle.toString())
                add(labrador.toString())
                add(leonberg.toString())
                add(lhasa.toString())
                add(malamute.toString())
                add(malinois.toString())
                add(maltese.toString())
                add(mastiff.toString())
                add(mexicanhairless.toString())
                add(mix.toString())
                add(mountain.toString())
                add(newfoundland.toString())
                add(otterhound.toString())
                add(ovcharka.toString())
                add(papillon.toString())
                add(pekinese.toString())
                add(pembroke.toString())
                add(pinscher.toString())
                add(pitbull.toString())
                add(pointer.toString())
                add(pomeranian.toString())
                add(poodle.toString())
                add(pug.toString())
                add(puggle.toString())
                add(pyrenees.toString())
                add(redbone.toString())
                add(retriever.toString())
                add(ridgeback.toString())
                add(rottweiler.toString())
            }
        }
    }

}