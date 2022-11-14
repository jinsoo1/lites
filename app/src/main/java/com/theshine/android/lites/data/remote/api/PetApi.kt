package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.PetGraphDataResponse
import com.theshine.android.lites.data.remote.model.response.PetResponse
import com.theshine.android.lites.data.remote.model.response.PetWeightResponse
import com.theshine.android.lites.data.remote.model.response.PetYearDataResponse
import io.reactivex.Single
import retrofit2.http.*

interface PetApi {


    @GET("/pet/Existence")
    fun petExistence(): Single<VoidResponse>

    @POST("/pet/insertPet")
    @FormUrlEncoded
    fun insertPet(
        @Field("type") type : String,
        @Field("name") name : String,
        @Field("birth") birth : String,
        @Field("variety") variety : String,
        @Field("gender") gender : Boolean,
        @Field("neutralization") neutralization : Boolean,
        @Field("bcs") bcs : Int,
        @Field("moisture") moisture : String,
        @Field("protein") protein : String,
        @Field("fat") fat : String,
        @Field("fiber") fiber : String,
        @Field("ash") ash : String

    ): Single<VoidResponse>

    @POST("/pet/insertMiniPet")
    @FormUrlEncoded
    fun insertMiniPet(
        @Field("type") type : String,
        @Field("name") name : String,
        @Field("birth") birth : String,
        @Field("variety") variety : String,
        @Field("gender") gender : Boolean,
        @Field("neutralization") neutralization : Boolean,
        @Field("bcs") bcs : Int
    ): Single<VoidResponse>

    @GET("/pet/myPet")
    fun getMyPet(): Single<DataResponse<PetResponse>>

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

}