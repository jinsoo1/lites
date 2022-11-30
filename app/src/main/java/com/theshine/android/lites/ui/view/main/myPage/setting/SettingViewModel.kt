package com.theshine.android.lites.ui.view.main.myPage.setting

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.SettingDataSource
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.rxkotlin.addTo

class SettingViewModel(
    private val settingDataSource: SettingDataSource
): BaseViewModel() {

    var isLoadedOnce: Boolean = false
    val isCommonAlertEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isMarketingAlertEnabled: MutableLiveData<Boolean> = MutableLiveData()

    init {
        settingDataSource
            .getNotificationSetting()
            .onUI {
                isCommonAlertEnabled.value = it.allowDefault == 1
                isMarketingAlertEnabled.value = it.allowMarketing == 1
                isLoadedOnce = true
            }
            .addTo(compositeDisposable)
    }

    fun updateSetting() {
        if (isLoadedOnce) settingDataSource
            .updateNotificationSetting(
                isCommonAlertEnabled.value ?: false,
                isMarketingAlertEnabled.value ?: false
            )
            .onUI { }
            .addTo(compositeDisposable)
    }


}