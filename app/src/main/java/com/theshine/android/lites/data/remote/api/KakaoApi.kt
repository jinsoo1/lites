package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.response.KakaoLocalResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApi {

    @GET("v2/local/search/keyword.json")
    fun keywordSearch(
        @Header("Authorization") apikey : String,
        @Query("query") query : String,
        @Query("y") y : String,
        @Query("x") x : String,
        @Query("radius") radius : Int,
        @Query("page") page : Int
    ) : Single<KakaoLocalResponse>
}