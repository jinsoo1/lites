package com.theshine.android.lites.data.remote.source

import com.theshine.android.lites.data.remote.api.FeedApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FeedDataSource(
    private val feedApi: FeedApi
) {

    fun sharedFeed(
        rowPerPage: Int,
        page: Int,
        region : String
    ) : Single<List<SharedInfoResponse>>{
        return feedApi.sharedFeed(rowPerPage, page, region)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun chatterFeed(
        rowPerPage: Int,
        page: Int,
        region: String
    ) : Single<List<SharedInfoResponse>>{
        return feedApi.chatterFeed(rowPerPage, page, region)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun detailFeed(
        feedToken : String,
        postState : String,
    ) : Single<DetailFeedResponse>{
        return feedApi.detailFeed(feedToken, postState)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun imageFeed(
        rowPerPage: Int,
        page: Int,
        region: String
    ) : Single<List<ImageFeedResponse>>{
        return feedApi.imageFeed(rowPerPage, page, region)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun feedUpload(
        title : RequestBody,
        content : RequestBody,
        category: RequestBody,
        region : RequestBody,
        photo: List<MultipartBody.Part>
    ) : Single<VoidResponse>{
        return feedApi.feedUpload(title, content, category, region, photo)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun postCommentToFeed(
        feedToken: String,
        comment: String
    ): Single<VoidResponse> {
        return feedApi.postCommentToFeed(feedToken, comment)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getComment(
        feedToken: String
    ) : Single<List<CommentResponse>>{
        return feedApi.getComment(feedToken)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProfile(
        petToken : String
    ) : Single<ProfileResponse>{
        return feedApi.getProfile(petToken)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUserSharedFeed(
        userToken : String,
        category : String
    ) : Single<List<SimpleSharedResponse>>{
        return feedApi.getUserSharedFeed(userToken, category)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUserSharedComment(
        userToken : String,
        category : String
    ) : Single<List<SimpleSharedResponse>>{
        return feedApi.getUserSharedComment(userToken, category)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun reportFeed(
        feedToken: String,
        reason: String
    ): Single<VoidResponse> {
        return feedApi.reportFeed(feedToken, reason)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun reportBadUser(
        title : String,
        content: String,
        badUserToken: String
    ): Single<VoidResponse> {
        return feedApi.reportBadUser(title, content, badUserToken)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }


}