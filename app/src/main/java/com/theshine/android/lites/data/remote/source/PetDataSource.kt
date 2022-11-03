package com.theshine.android.lites.data.remote.source

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
        height : String,
        waist : String,
        bcs : Int
    ) : Single<VoidResponse>{
        return petApi.insertPet(type, name, birth, variety, gender, neutralization, height, waist, bcs)
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



}