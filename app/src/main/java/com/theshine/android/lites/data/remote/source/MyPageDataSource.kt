package com.theshine.android.lites.data.remote.source

import com.theshine.android.lites.data.remote.api.MyPageApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.EventResponse
import com.theshine.android.lites.data.remote.model.response.InquiryResponse
import com.theshine.android.lites.data.remote.model.response.NoticeResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers.io
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io


class MyPageDataSource(
    private val myPageApi: MyPageApi
) {

    fun eventList(
        rowPerPage: Int,
        page: Int
    ): Single<List<EventResponse>> {
        return myPageApi.eventList(rowPerPage, page)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getEventDetail(
        eventToken: String
    ): Single<EventResponse> {
        return myPageApi.getEventDetail(eventToken)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun noticeList(
        rowPerPage: Int,
        page: Int
    ): Single<List<NoticeResponse>> {
        return myPageApi.noticeList(rowPerPage, page)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }
}