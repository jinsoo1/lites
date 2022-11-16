package com.theshine.android.lites.data.remote.source

import com.theshine.android.lites.data.remote.api.PetApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

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
        type : RequestBody,
        name : RequestBody,
        birth : RequestBody,
        variety : RequestBody,
        gender : RequestBody,
        neutralization : RequestBody,
        bcs : RequestBody,
        moisture : RequestBody,
        protein : RequestBody,
        fat : RequestBody,
        fiber : RequestBody,
        ash : RequestBody,
        profileImg: MultipartBody.Part?

        ) : Single<VoidResponse>{
        return petApi.insertPet(type, name, birth, variety, gender, neutralization, bcs, moisture, protein, fat, fiber, ash, profileImg)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertMiniPet(
        type : RequestBody,
        name : RequestBody,
        birth : RequestBody,
        variety : RequestBody,
        gender : RequestBody,
        neutralization : RequestBody,
        bcs : RequestBody,
        profileImg: MultipartBody.Part?
    ) : Single<VoidResponse>{
        return petApi.insertMiniPet(type, name, birth, variety, gender, neutralization, bcs, profileImg)
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

    fun getMyPetProfile(
//        petToken : String
    ) : Single<ProfileListResponse>{
        return petApi.getMyPetProfile()
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }
    //petToken

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

    fun getMyPetList(
    ) : Single<List<ProfileListResponse>> {
        return petApi.getMyPetList()
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updatePetProfile(
        petToken: RequestBody,
        name: RequestBody,
        birth: RequestBody,
        variety: RequestBody,
        profileImage: MultipartBody.Part?,
        gender: RequestBody,
        neutralization: RequestBody,
        bcs: RequestBody
    ) : Single<VoidResponse>{
        return petApi.updatePetProfile(petToken, name, birth, variety, profileImage, gender, neutralization, bcs)
            .subscribeOn(Schedulers.io())
            .map { it }
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

    fun getBLE_state() :Single<BLEStateResponse>{
        return petApi.getBLE_state()
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())

    }

}