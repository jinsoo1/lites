package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface FeedApi {

    @GET("/feed/shared")
    fun sharedFeed(
        @Query("rowPerPage") rowPerPage: Int,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Single<DataResponse<List<SharedInfoResponse>>>

    @GET("/feed/chatter")
    fun chatterFeed(
        @Query("rowPerPage") rowPerPage: Int,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Single<DataResponse<List<SharedInfoResponse>>>

    @GET("/feed/detailfeed")
    fun detailFeed(
        @Query("feedToken") feedToken: String,
        @Query("category") category: String,
    ): Single<DataResponse<DetailFeedResponse>>

    @GET("/feed/imagefeed")
    fun imageFeed(
        @Query("rowPerPage") rowPerPage: Int,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Single<DataResponse<List<ImageFeedResponse>>>


    @Multipart
    @POST("/feed/create")
    fun feedUpload(
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody,
        @Part("category") category: RequestBody,
        @Part("region") region: RequestBody,
        @Part photo: List<MultipartBody.Part>
    ): Single<VoidResponse>


    @POST("/feed/{feedToken}/comment")
    @FormUrlEncoded
    fun postCommentToFeed(
        @Path("feedToken") feedToken: String,
        @Field("comment") comment: String
    ): Single<VoidResponse>

    @GET("/feed/{feedToken}/comment")
    fun getComment(
        @Path("feedToken") feedToken: String
    ): Single<DataResponse<List<CommentResponse>>>

    @GET("/feed/{petToken}/profile")
    fun getProfile(
        @Path("petToken") petToken: String
    ): Single<DataResponse<ProfileResponse>>

    @GET("/feed/{userToken}/sharedfeed")
    fun getUserSharedFeed(
        @Path("userToken") userToken: String,
        @Query("category") category: String
    ): Single<DataResponse<List<SimpleSharedResponse>>>

    @GET("/feed/{userToken}/sharedcomment")
    fun getUserSharedComment(
        @Path("userToken") userToken: String,
        @Query("category") category: String
    ): Single<DataResponse<List<SimpleSharedResponse>>>

    @POST("/feed/report")
    @FormUrlEncoded
    fun reportFeed(
        @Field("feedToken") feedToken: String,
        @Field("reason") reason: String
    ): Single<VoidResponse>

    @POST("/feed/baduserinquiry")
    @FormUrlEncoded
    fun reportBadUser(
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("badUserToken") badUserToken: String
    ): Single<VoidResponse>

}
