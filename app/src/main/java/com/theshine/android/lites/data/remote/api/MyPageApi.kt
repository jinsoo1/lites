package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.response.EventResponse
import com.theshine.android.lites.data.remote.model.response.InquiryResponse
import com.theshine.android.lites.data.remote.model.response.NoticeResponse
import com.theshine.android.lites.data.remote.source.MyPageDataSource
import io.reactivex.Single
import retrofit2.http.*

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

    @GET("/notice/noticelist")
    fun noticeList(
        @Query("rowPerPage") rowPerPage: Int,
        @Query("page") page: Int
    ) : Single<DataResponse<List<NoticeResponse>>>

    @POST("/inquiry")
    @FormUrlEncoded
    fun postInquiry(
        @Field("inquiryToken") inquiryToken: String,
        @Field("title") title: String,
        @Field("createdAt") createdAt: String,
        @Field("content") content: String,
    ): Single<DataResponse<InquiryResponse>>


}