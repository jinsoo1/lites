package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface PetApi {


    @GET("/pet/Existence")
    fun petExistence(): Single<VoidResponse>

    @Multipart
    @PATCH("/pet/insertPet")
    fun insertPet(
        @Part("type") type : RequestBody,
        @Part("name") name : RequestBody,
        @Part("birth") birth : RequestBody,
        @Part("variety") variety : RequestBody,
        @Part("gender") gender : RequestBody,
        @Part("neutralization") neutralization : RequestBody,
        @Part("bcs") bcs : RequestBody,
        @Part("moisture") moisture : RequestBody,
        @Part("protein") protein : RequestBody,
        @Part("fat") fat : RequestBody,
        @Part("fiber") fiber : RequestBody,
        @Part("ash") ash : RequestBody,
        @Part profileImg: MultipartBody.Part?

        ): Single<VoidResponse>

    @Multipart
    @PATCH("/pet/insertMiniPet")
    fun insertMiniPet(
        @Part("type") type : RequestBody,
        @Part("name") name : RequestBody,
        @Part("birth") birth : RequestBody,
        @Part("variety") variety : RequestBody,
        @Part("gender") gender : RequestBody,
        @Part("neutralization") neutralization : RequestBody,
        @Part("bcs") bcs : RequestBody,
        @Part profileImg: MultipartBody.Part?,
    ): Single<VoidResponse>

    @GET("/pet/myPet")
    fun getMyPet(): Single<DataResponse<PetResponse>>

    @GET("/pet/myPetProfile")
    fun getMyPetProfile(
//        @Path("petToken") petToken: String
    ): Single<DataResponse<ProfileListResponse>>

    @POST("/pet/weight")
    @FormUrlEncoded
    fun savePetWeight(
        @Field("petToken") petToken : String,
        @Field("weight") weight : Double
    ): Single<VoidResponse>


    @GET("/pet/{petToken}/myPetWeight")
    fun getMyPetWeight(
        @Path("petToken") petToken: String
    ): Single<DataResponse<PetWeightResponse>>

    @GET("/pet/weekPetdata")
    fun getWeekPetData() : Single<DataResponse<List<PetGraphDataResponse>>>

    @GET("/pet/monthPetdata")
    fun getMonthPetData() : Single<DataResponse<List<PetGraphDataResponse>>>

    @GET("/pet/yearPetdata")
    fun getYearPetData() : Single<DataResponse<List<PetYearDataResponse>>>


    @GET("/pet/{petToken}")
    fun getMyPetList(
        @Path("petToken") petToken : String
    ): Single<DataResponse<List<PetResponse>>>

    @GET("/pet/petlist")
    fun getMyPetList(
    ): Single<DataResponse<List<ProfileListResponse>>>

    @Multipart
    @PATCH("/pet/updatePet")
    fun updatePetProfile(
        @Part("petToken") petToken: RequestBody,
        @Part("name") name: RequestBody,
        @Part("birth") birth: RequestBody,
        @Part("variety") variety: RequestBody,
        @Part profileImg: MultipartBody.Part?,
        @Part("gender") gender: RequestBody,
        @Part("neutralization") neutralization: RequestBody,
        @Part("bcs") bcs: RequestBody
    ) : Single<VoidResponse>

    @POST("/pet/ingredient")
    @FormUrlEncoded
    fun updateIngredient(
        @Field("petToken") petToken : String,
        @Field("moisture") moisture: String,
        @Field("protein") protein: String,
        @Field("fat") fat: String,
        @Field("fiber") fiber: String,
        @Field("ash") ash: String
    ): Single<VoidResponse>

    @GET("/pet/BLEstate")
    fun getBLE_state(
    ): Single<DataResponse<BLEStateResponse>>
}