package com.theshine.android.lites.data.remote.source

import com.theshine.android.lites.data.remote.api.SettingApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.InquiryResponse
import com.theshine.android.lites.data.remote.model.response.NotificationSettingResponse
import com.theshine.android.lites.util.ext.mapData
import com.theshine.android.lites.util.ext.observeOnMainThread
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingDataSource(
    private val settingApi: SettingApi
) {

    fun getNotificationSetting(): Single<NotificationSettingResponse> = settingApi.getNotificationSetting().mapData().observeOnMainThread()

    fun updateNotificationSetting(
        allowDefault: Boolean,
        allowMarketing: Boolean
    ): Single<VoidResponse> = settingApi.updateNotificationSetting(allowDefault, allowMarketing).observeOnMainThread()


    fun getInquiryList(): Single<List<InquiryResponse>> = settingApi.getInquiryList().mapData().observeOnMainThread()

    fun getInquiryDetail(
        inquiryToken : String
    ) : Single<InquiryResponse> {
        return settingApi.getInquiryDetail(inquiryToken)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun postInquiry(
        title : String,
        content: String
    ) :Single<VoidResponse>{
        return settingApi.postInquiry(title, content)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }
}
