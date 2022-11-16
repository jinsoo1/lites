package com.theshine.android.lites.ui.view.main.myPage.info

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel

class MyPageInfoViewModel: BaseViewModel() {
    val currentVersion = MutableLiveData<String>()
    val latestVersion = MutableLiveData<String>()
}