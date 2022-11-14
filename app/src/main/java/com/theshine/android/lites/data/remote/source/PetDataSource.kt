package com.theshine.android.lites.data.remote.source

import com.theshine.android.lites.data.remote.api.PetApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.PetGraphDataResponse
import com.theshine.android.lites.data.remote.model.response.PetResponse
import com.theshine.android.lites.data.remote.model.response.PetWeightResponse
import com.theshine.android.lites.data.remote.model.response.PetYearDataResponse
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

    fun insertMiniPet(
        type : String,
        name : String,
        birth : String,
        variety : String,
        gender : Boolean,
        neutralization : Boolean,
        bcs : Int
    ) : Single<VoidResponse>{
        return petApi.insertMiniPet(type, name, birth, variety, gender, neutralization, bcs)
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

    fun savePetWeight(
        petToken : String,
        weight : Double
    ) : Single<VoidResponse>{
        return petApi.savePetWeight(petToken, weight)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMyPetWeight(
        petToken : String
    ) : Single<PetWeightResponse>{
        return petApi.getMyPetWeight(petToken)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getWeekPetData() : Single<List<PetGraphDataResponse>>{
        return petApi.getWeekPetData()
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())

    }

    fun getMonthPetData() : Single<List<PetGraphDataResponse>>{
        return petApi.getMonthPetData()
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getYearPetData() : Single<List<PetYearDataResponse>>{
        return petApi.getYearPetData()
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

    fun updateIngredient(
        petToken : String,
        moisture: String,
        protein: String,
        fat: String,
        fiber: String,
        ash: String
    ) : Single<VoidResponse>{
        return petApi.updateIngredient(petToken, moisture, protein, fat, fiber, ash)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

}