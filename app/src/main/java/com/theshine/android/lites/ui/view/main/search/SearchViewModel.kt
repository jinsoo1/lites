package com.theshine.android.lites.ui.view.main.search

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel

class SearchViewModel: BaseViewModel() {

    val clickState: MutableLiveData<Boolean> = MutableLiveData(false) //false는 클릭하지 않은 상태, true는 클릭한 상태


}