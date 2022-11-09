package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.response.EventResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyPageApi {

    @GET("/event")
    fun eventList(
        @Query("rowPerPage") rowPerPage: Int,
        @Query("page") page: Int
    ) : Single<DataResponse<List<EventResponse>>>

    @GET("/event/{eventToken}")
    fun getEventDetail(
        @Path("eventToken") eventToken: String
    ) : Single<DataResponse<EventResponse>>



}