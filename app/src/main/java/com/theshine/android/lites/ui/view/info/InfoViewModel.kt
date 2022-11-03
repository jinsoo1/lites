package com.theshine.android.lites.ui.view.info

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel

class InfoViewModel : BaseViewModel() {

    val type: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val birth: MutableLiveData<String> = MutableLiveData()
    val variety : MutableLiveData<String> = MutableLiveData()
    val gender: MutableLiveData<Boolean> = MutableLiveData() //여아(false), 남아(true)
    val neutralization: MutableLiveData<Boolean> = MutableLiveData() //중성화 안함(false), 중성화 함(true)
    val height: MutableLiveData<String> = MutableLiveData()
    val waist: MutableLiveData<String> = MutableLiveData()
    val bcs : MutableLiveData<Int> = MutableLiveData()
}