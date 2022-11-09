package com.theshine.android.lites.data.remote.source

import com.google.common.math.IntMath
import com.theshine.android.lites.data.remote.api.PetApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.PetResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PetDataSource(
    private val petApi: PetApi
) {

    fun petExistence() : Single<VoidResponse>{
        return petApi.petExistence()
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertPet(
        type : String,
        name : String,
        birth : String,
        variety : String,
        gender : Boolean,
        neutralization : Boolean,
        bcs : Int,
        moisture : String,
        protein : String,
        fat : String,
        fiber : String,
        ash : String

    ) : Single<VoidResponse>{
        return petApi.insertPet(type, name, birth, variety, gender, neutralization, bcs, moisture, protein, fat, fiber, ash)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMyPet() : Single<PetResponse>{
        return petApi.getMyPet()
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMyPetList(
        petToken: String
    ) : Single<List<PetResponse>> {
        return petApi.getMyPetList(petToken)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

}