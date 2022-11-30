package com.theshine.android.lites.ui.view.main.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class CommunityViewModel: BaseViewModel() {

    private val _action : MutableLiveData<Event<CommunityAction>> = MutableLiveData()
    val action : LiveData<Event<CommunityAction>> get() = _action

    private val _region : MutableLiveData<String> = MutableLiveData("전체지역")
    val region : LiveData<String> get() = _region



    fun setRegionAction(){
        _action.value = Event(CommunityAction.REGION)
    }

    suspend fun settingRegion(region : String){
        _region.value = region
    }



    enum class CommunityAction{
        REGION
    }
}