package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.InquiryResponse
import com.theshine.android.lites.data.remote.model.response.NotificationSettingResponse
import io.reactivex.Single
import retrofit2.http.*

interface SettingApi {

    @GET("/setting/inquiry/list")
    fun getInquiryList(): Single<DataResponse<List<InquiryResponse>>>

    @GET("/setting/inquiry")
    fun getInquiryDetail(
        @Query("inquiryToken") inquiryToken: String
    ): Single<DataResponse<InquiryResponse>>

    @POST("/setting/inquiry")
    @FormUrlEncoded
    fun postInquiry(
        @Field("title") title: String,
        @Field("content") content: String,
    ): Single<VoidResponse>

    @GET("/setting/notification")
    fun getNotificationSetting(): Single<DataResponse<NotificationSettingResponse>>

    @PATCH("/setting/notification")
    @FormUrlEncoded
    fun updateNotificationSetting(
        @Field("allowDefault") allowDefault: Boolean,
        @Field("allowMarketing") allowMarketing: Boolean
    ): Single<VoidResponse>
}