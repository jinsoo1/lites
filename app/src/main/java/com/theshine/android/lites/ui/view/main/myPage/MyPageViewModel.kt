package com.theshine.android.lites.ui.view.main.myPage

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel

class MyPageViewModel: BaseViewModel() {

    val tabState: MutableLiveData<Boolean> = MutableLiveData(false) //false이면 정보공유탭, true이면 수다방탭
}